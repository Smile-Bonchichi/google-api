package com.test.testParserIdCard.service.impl;

import com.test.testParserIdCard.dto.RequestDto;
import com.test.testParserIdCard.dto.ResponseDto;
import com.test.testParserIdCard.model.*;
import com.test.testParserIdCard.service.IdCardService;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import org.apache.axis.encoding.Base64;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class IdCardServiceImpl implements IdCardService {
    final String url = "";

    final RestTemplate restTemplate = new RestTemplate();

    @Override
    public ResponseDto getFrontSide(RequestDto requestDto) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<GoogleApiRequestModel> googleApiModelHttpEntity = null;
        try {
            googleApiModelHttpEntity = new HttpEntity<>(
                    GoogleApiRequestModel.builder()
                            .requests(Collections.singletonList(
                                    GoogleRequestApiModel.builder()
                                            .image(GoogleImageApiModel.builder()
                                                    .content(parseImageToBase64(requestDto.getImage()))
                                                    .build())
                                            .features(GoogleFeaturesApiModel.builder()
                                                    .type("DOCUMENT_TEXT_DETECTION")
                                                    .maxResults(1L)
                                                    .build())
                                            .imageContext(GoogleLanguageApiModel.builder()
                                                    .languageHints(Collections.singletonList("ru"))
                                                    .build())
                                            .build())
                            ).build(),
                    httpHeaders
            );
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        ResponseEntity<GoogleResponseApiModel> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.POST,
                googleApiModelHttpEntity,
                GoogleResponseApiModel.class
        );

        PassportResponseModel passportResponseModel = parseIdCardBackSide(responseEntity.getBody().getResponses().get(0).getTextAnnotations());

        return null;
    }

    private PassportResponseModel parseIdCardFrontSide(List<GoogleDescApiModel> googleDescApiModels) {
        PassportResponseModel passportResponseModel = new PassportResponseModel();

        for (int i = 0; i < googleDescApiModels.size(); i++) {
            if (googleDescApiModels.get(i).getDescription().equalsIgnoreCase("фамилия") &&
                    googleDescApiModels.get(i + 1).getDescription().equals("/") &&
                    googleDescApiModels.get(i + 2).getDescription().equalsIgnoreCase("surname")) {
                passportResponseModel.setSurname(googleDescApiModels.get(i += 3).getDescription());
            } else if (googleDescApiModels.get(i).getDescription().equalsIgnoreCase("имя") &&
                    googleDescApiModels.get(i + 1).getDescription().equals("/") &&
                    googleDescApiModels.get(i + 2).getDescription().equalsIgnoreCase("name")) {
                passportResponseModel.setName(googleDescApiModels.get(i += 3).getDescription());
            } else if (googleDescApiModels.get(i).getDescription().equalsIgnoreCase("отчество") &&
                    googleDescApiModels.get(i + 1).getDescription().equals("/") &&
                    googleDescApiModels.get(i + 2).getDescription().equalsIgnoreCase("patronymic")) {
                if (googleDescApiModels.get(i += 3).getDescription().equalsIgnoreCase("жынысы")) {
                    passportResponseModel.setSurname(passportResponseModel.getSurname() + " уулу");
                    passportResponseModel.setPatronymic("");
                } else {
                    passportResponseModel.setPatronymic(googleDescApiModels.get(i).getDescription());
                }
            } else if (googleDescApiModels.get(i).getDescription().equals("/") &&
                    googleDescApiModels.get(i + 1).getDescription().equalsIgnoreCase("document") &&
                    googleDescApiModels.get(i + 2).getDescription().equals("#")) {
                passportResponseModel.setId(googleDescApiModels.get(i += 3).getDescription());
            } else if (googleDescApiModels.get(i).getDescription().equalsIgnoreCase("date") &&
                    googleDescApiModels.get(i + 1).getDescription().equalsIgnoreCase("of") &&
                    googleDescApiModels.get(i + 2).getDescription().equalsIgnoreCase("birth")) {
                try {
                    passportResponseModel.setDateOfBirth(
                            LocalDate.parse(googleDescApiModels.get(i += 3).getDescription(),
                                    DateTimeFormatter.ofPattern("dd.MM.yyyy"))
                    );
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if ((googleDescApiModels.get(i).getDescription().equalsIgnoreCase("N") ||
                    googleDescApiModels.get(i).getDescription().equalsIgnoreCase("№")) &&
                    googleDescApiModels.get(i + 1).getDescription().equals("/")) {
                passportResponseModel.setDateOfBirth(
                        LocalDate.parse(googleDescApiModels.get(i += 2).getDescription(),
                                DateTimeFormatter.ofPattern("dd.MM.yyyy"))
                );
            } else if (googleDescApiModels.get(i).getDescription().equalsIgnoreCase("date") &&
                    googleDescApiModels.get(i + 1).getDescription().equalsIgnoreCase("of") &&
                    googleDescApiModels.get(i + 2).getDescription().equalsIgnoreCase("expiry")) {
                passportResponseModel.setDateOfExpiry(
                        LocalDate.parse(googleDescApiModels.get(i += 3).getDescription(),
                                DateTimeFormatter.ofPattern("dd.MM.yyyy"))
                );
            }
        }

        System.out.println(passportResponseModel);

        return null;
    }

    private PassportResponseModel parseIdCardBackSide(List<GoogleDescApiModel> googleDescApiModels) {
        PassportResponseModel passportResponseModel = new PassportResponseModel();

        for (int i = 0; i < googleDescApiModels.size(); i++) {
            if (googleDescApiModels.get(i).getDescription().equalsIgnoreCase("Personal") &&
                    googleDescApiModels.get(i + 1).getDescription().equalsIgnoreCase("number")) {
                passportResponseModel.setInn(googleDescApiModels.get(i += 2).getDescription());
            } else if (googleDescApiModels.get(i).getDescription().equalsIgnoreCase("MKK")) {
                passportResponseModel.setMkk(googleDescApiModels.get(i += 1).getDescription());
            } else if (googleDescApiModels.get(i).getDescription().equalsIgnoreCase("date") &&
                    googleDescApiModels.get(i + 1).getDescription().equalsIgnoreCase("of") &&
                    googleDescApiModels.get(i + 2).getDescription().equalsIgnoreCase("issue")) {
                passportResponseModel.setDateOfIssue(
                        LocalDate.parse(googleDescApiModels.get(i += 3).getDescription(),
                                DateTimeFormatter.ofPattern("dd.MM.yyyy"))
                );
            }
        }

        System.out.println(passportResponseModel);

        return null;
    }

    @Override
    public ResponseDto getBackSide(RequestDto requestDto) {
        return null;
    }

    private String parseImageToBase64(MultipartFile image) throws IOException {
        return Base64.encode(image.getBytes());
    }
}
