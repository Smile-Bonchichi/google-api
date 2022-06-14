package com.test.testParserIdCard.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PassportResponseModel {
    String name;

    String surname;

    String patronymic;

    String inn;

    String id;

    String mkk;

    String gender;

    String nationality;

    String placeOfBirth;

    LocalDate dateOfBirth;

    LocalDate dateOfIssue;

    LocalDate dateOfExpiry;
}
