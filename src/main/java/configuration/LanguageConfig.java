package configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Map;

@ConfigurationProperties(prefix = "app")
@Component
@Data
public class LanguageConfig {

    private Locale locale;
    private String fileName;
    private Map<String, String> langMap;

    public Locale getLocale() {
        if (locale != null) {
            return locale;
        }
        return Locale.ENGLISH;
    }

    public String getFileName() {
        if (langMap != null && !langMap.isEmpty())
            for (Map.Entry<String, String> entry : langMap.entrySet()) {
                if (locale == null) {
                    getLocale();
                }
                if (locale != null && locale.toString().equals(entry.getKey())) {
                    return fileName + "_" + entry.getKey() + ".csv";
                }
            }
        return "questions/questions_en_US.csv";
    }
}
