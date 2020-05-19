package service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import service.interfaces.Message;

import java.util.*;

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
    public void init() {
        if (getLocale() == null || getLanguage().equalsIgnoreCase("en")) {
            setLocale(Locale.ENGLISH);
        } else {
            setLocale(null);
        }
    }

    @Override
    public String getMessage(String textBundle) {
        return messageSource.getMessage(textBundle, new Object[]{}, locale);
    }

    @Override
    public String getFileName() {
        return locale == Locale.ENGLISH ? fileNameEN : fileNameRu;
    }

    @Override
    public String getHelloMessage(int size) {
        return messageSource.getMessage("test.hello", new Object[]{}, locale) + "\n" +
                messageSource.getMessage("test.consists", new Object[]{size}, locale) + "\n" +
                messageSource.getMessage("test.rules", new Object[]{}, locale) + "\n";
    }

    @Override
    public String getErrorMessage() {
        return messageSource.getMessage("error", new Object[]{}, locale);
    }

    @Override
    public String getResultMessage(boolean passed, int numOfRightAnswers, int numOfQuestions) {
        String result = messageSource.getMessage("test.completed", new Object[]{}, locale) + "\n";

        if (passed) {
            result+= messageSource.getMessage("test.result.pass", new Object[]{}, locale) + "\n";
        } else {
            result+= messageSource.getMessage("test.result.failed", new Object[]{}, locale) + "\n";
        }
            result+= messageSource.getMessage("test.result", new Object[]{numOfRightAnswers, numOfQuestions}, locale) + "\n";
    return result;
    }

}
