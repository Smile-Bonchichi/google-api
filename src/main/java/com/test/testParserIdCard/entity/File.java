package com.test.testParserIdCard.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Table(name = "files")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String fileName;

    String fileUrl;
}
