package ru.imatveev.simpletranslator.domain.entity;

import lombok.Data;

import java.util.List;

@Data
public class Translations {
    private List<Translation> translations;
}
