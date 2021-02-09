package ru.imatveev.simpletranslator.client;

import feign.RequestInterceptor;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.imatveev.simpletranslator.domain.entity.Data;
import ru.imatveev.simpletranslator.domain.entity.Languages;
import ru.imatveev.simpletranslator.domain.entity.TranslateFilter;
import ru.imatveev.simpletranslator.domain.entity.Translations;

@FeignClient(
        url = "${rapidApi.googleTranslator.uri}",
        name = "simple-translator",
        configuration = GoogleTranslatorClient.GoogleClientConfiguration.class
)
public interface GoogleTranslatorClient {
    @GetMapping("/languages")
    ResponseEntity<Data<Languages>> languages();

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    ResponseEntity<Data<Translations>> translate(TranslateFilter filter);

    class GoogleClientConfiguration {
        @Bean
        RequestInterceptor requestInterceptor(@Value("${rapidApi.login}") String login,
                                              @Value("${rapidApi.xKey}") String xKey) {
            return template -> template.header(login, xKey);
        }

        @Bean
        Encoder feignFormEncoder(ObjectFactory<HttpMessageConverters> converter) {
            return new SpringFormEncoder(new SpringEncoder(converter));
        }
    }
}
