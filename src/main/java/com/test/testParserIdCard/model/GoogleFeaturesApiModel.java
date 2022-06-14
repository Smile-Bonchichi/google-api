package com.test.testParserIdCard.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GoogleFeaturesApiModel {
    String type;

    Long maxResults;
}
