package ru.imatveev.simpletranslator.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Translation {
    private String translatedText;
    private String detectedSourceLanguage;
}
