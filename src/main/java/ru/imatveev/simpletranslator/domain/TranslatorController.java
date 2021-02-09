package ru.imatveev.simpletranslator.domain;

import ru.imatveev.simpletranslator.domain.entity.Language;
import ru.imatveev.simpletranslator.domain.entity.TranslateFilter;
import ru.imatveev.simpletranslator.domain.entity.Translation;

import java.util.List;

public interface TranslatorController {
    List<Language> languages();

    List<Translation> translate(TranslateFilter filter);
}
