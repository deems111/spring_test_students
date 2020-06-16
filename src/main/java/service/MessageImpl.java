package service;

import configuration.LanguageConfig;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import service.interfaces.Message;

/**
 * Service for MessageSource
 */
@Data
@Service
public class MessageImpl implements Message {

    private final MessageSource messageSource;
    private final LanguageConfig languageConfig;

    @Autowired
    public MessageImpl(MessageSource messageSource, LanguageConfig languageConfig) {
        this.messageSource = messageSource;
        this.languageConfig = languageConfig;
    }

    @Override
    public String getMessage(String textBundle, Object... objects) {
        return messageSource.getMessage(textBundle, objects, languageConfig.getLocale());
    }

    @Override
    public String getFileName() {
        return languageConfig.getFileName();
    }


}
