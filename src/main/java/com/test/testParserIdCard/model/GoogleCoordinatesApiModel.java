package com.test.testParserIdCard.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GoogleCoordinatesApiModel {
    Long x;

    Long y;
}
