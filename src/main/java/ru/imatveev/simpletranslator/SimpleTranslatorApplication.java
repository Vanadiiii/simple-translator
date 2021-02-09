package ru.imatveev.simpletranslator;

import com.google.gson.Gson;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import ru.imatveev.simpletranslator.domain.TranslatorController;
import ru.imatveev.simpletranslator.domain.entity.TranslateFilter;

@SpringBootApplication
@EnableFeignClients
public class SimpleTranslatorApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(SimpleTranslatorApplication.class, args);
        TranslatorController translator = run.getBean(TranslatorController.class);
        System.out.println(new Gson().toJson(translator.languages()));

        TranslateFilter requestBody = TranslateFilter.builder()
                .q("I'll be the best Java-developer")
                .target("ru")
                .build();
        System.out.println(new Gson().toJson(translator.translate(requestBody)));
    }
}
