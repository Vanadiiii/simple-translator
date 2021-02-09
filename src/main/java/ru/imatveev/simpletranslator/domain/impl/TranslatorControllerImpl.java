package ru.imatveev.simpletranslator.domain.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import ru.imatveev.simpletranslator.client.GoogleTranslatorClient;
import ru.imatveev.simpletranslator.domain.TranslatorController;
import ru.imatveev.simpletranslator.domain.entity.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class TranslatorControllerImpl implements TranslatorController {
    private final GoogleTranslatorClient googleTranslatorClient;

    @Override
    public List<Language> languages() {
        return Optional.ofNullable(googleTranslatorClient.languages())
                .map(ResponseEntity::getBody)
                .map(Data::getData)
                .map(Languages::getLanguages)
                .orElseThrow(() -> new RuntimeException("Can't get languages"));
    }

    @Override
    public List<Translation> translate(TranslateFilter filter) {
        return Optional.ofNullable(googleTranslatorClient.translate(filter))
                .map(ResponseEntity::getBody)
                .map(Data::getData)
                .map(Translations::getTranslations)
                .stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }
}
