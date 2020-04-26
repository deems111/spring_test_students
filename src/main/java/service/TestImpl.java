package service;

import controller.StudentTestControllerImpl;
import dao.TestQuestionDaoImpl;
import dto.TestQuestion;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import service.interfaces.Test;

import java.util.Locale;
import java.util.Set;


/**
 * Implementation of Test for students testing
 */
@Data
@Service
public class TestImpl implements Test {

    @Autowired
    private final StudentTestControllerImpl controller;
    @Autowired
    private final TestQuestionDaoImpl questionDao;
    @Autowired
    private final MessageSource messageSource;

    @Value("${file.name.en}")
    private String fileNameEN;

    @Value("${file.name.ru}")
    private String fileNameRu;

    @Value("${language}")
    private String language;

    private Locale locale;

    //before testing
    private void init(int size) {
        if (size == 0) {
            System.out.println(messageSource.getMessage("test.error", new Object[]{}, locale));
            return;
        }
        System.out.println(messageSource.getMessage("test.hello", new Object[]{}, locale));
        System.out.println(messageSource.getMessage("test.consists", new Object[]{size}, locale));
        System.out.println(messageSource.getMessage("test.rules", new Object[]{}, locale));
    }

    //access to DAO
    public Set<TestQuestion> getDao() {
        return questionDao.getQuestions(locale == Locale.ENGLISH ? fileNameEN : fileNameRu);
    }

    @Override
    public void test() {
        initLocale();
        Set<TestQuestion> testQuestions = getDao();
        int numOfQuestions = testQuestions.size();
        int numOfRightAnswers = 0;
        init(numOfQuestions);
        for (TestQuestion question : testQuestions) {
            String answer = controller.test(question.getQuestion());
            if (question.getRightAnswer().equals(answer.trim())) {
                numOfRightAnswers++;
            }
        }

        System.out.println(messageSource.getMessage("test.completed", new Object[]{}, locale));
        if (numOfRightAnswers == 0) {
            System.out.println(messageSource.getMessage("test.result.failed", new Object[]{}, locale));
        } else {
            System.out.println(messageSource.getMessage("test.result.pass", new Object[]{}, locale));
        }
        System.out.println(messageSource.getMessage("test.result", new Object[]{numOfRightAnswers, numOfQuestions}, locale));
    }

    @Override
    public void initLocale() {
        if (getLocale() == null || getLanguage().equalsIgnoreCase("en")) {
            setLocale(Locale.ENGLISH);
        } else {
            setLocale(null);
        }
    }

}
