package ru.imatveev.simpletranslator.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TranslateFilter {
    @NotNull
    @JsonProperty("q")
    private String q;
    private String target;
    private String model;
    private String format;
    @NotNull
    private String source;

}
