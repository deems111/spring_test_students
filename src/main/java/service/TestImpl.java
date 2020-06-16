package service;

import configuration.LanguageConfig;
import controller.StudentTestControllerImpl;
import dao.TestQuestionDaoImpl;
import dto.TestQuestion;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.interfaces.Test;
import utility.LocaleUtil;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Locale;
import java.util.Set;


/**
 * Implementation of Test for students testing
 */
@Data
@Service
public class TestImpl implements Test {

    private final StudentTestControllerImpl controller;
    private final TestQuestionDaoImpl questionDao;
    private final MessageImpl messageSourceService;
    private final PrintImpl printService;
    private final LanguageConfig languageConfig;

    @Autowired
    public TestImpl(StudentTestControllerImpl controller, TestQuestionDaoImpl questionDao,
                    MessageImpl messageSourceService, PrintImpl printService, LanguageConfig languageConfig) {
        this.controller = controller;
        this.questionDao = questionDao;
        this.messageSourceService = messageSourceService;
        this.printService = printService;
        this.languageConfig = languageConfig;
    }

    public void startTest(int size) {
        if (size == 0) {
            printService.printLine(messageSourceService.getMessage("error"));
            return;
        }

        printService.printLine(messageSourceService.getMessage("test.hello"));
        printService.printLine(messageSourceService.getMessage("test.consists", size));
        printService.printLine(messageSourceService.getMessage("test.rules"));
    }

    //access to DAO
    public Set<TestQuestion> getDao() {
        return questionDao.getQuestions(messageSourceService.getFileName());
    }

    @Override
    public void test(PrintStream out, InputStream in) {
        setStreams(out, in);
        Set<TestQuestion> testQuestions = getDao();
        int numOfQuestions = testQuestions.size();
        int numOfRightAnswers = 0;
        startTest(numOfQuestions);
        for (TestQuestion question : testQuestions) {
            String answer = controller.test(question.getQuestion());
            if (question.getRightAnswer().equals(answer.trim())) {
                numOfRightAnswers++;
            }
        }

        printService.printLine(messageSourceService.getMessage("test.completed"));
        if (numOfRightAnswers == 0) {
            printService.printLine(messageSourceService.getMessage("test.result.failed"));
        } else {
            printService.printLine(messageSourceService.getMessage("test.result.pass"));
        }
        printService.printLine(messageSourceService.getMessage("test.result", numOfRightAnswers, numOfQuestions));
    }

    @Override
    public void setStreams(PrintStream out, InputStream in) {
        printService.setIn(in);
        printService.setOut(out);
        controller.setIn(in);
    }

}
