package service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import service.interfaces.Message;

import java.util.Locale;

/**
 * Service for MessageSource
 */
@Data
@Service
public class MessageImpl implements Message {

    private final MessageSource messageSource;

    @Value("${file.name.en}")
    private String fileNameEN;

    @Value("${file.name.ru}")
    private String fileNameRu;

    @Value("${language}")
    private String language;

    private Locale locale;

    @Autowired
    public MessageImpl(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public String getMessage(String textBundle, Object... objects) {
        return messageSource.getMessage(textBundle, objects, locale);
    }

    @Override
    public String getFileName() {
        if (getLocale() == null || getLanguage().equalsIgnoreCase("en")) {
            setLocale(Locale.ENGLISH);
        } else {
            setLocale(null);
        }
        return locale == Locale.ENGLISH ? fileNameEN : fileNameRu;
    }

}
