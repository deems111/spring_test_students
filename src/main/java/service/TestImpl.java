package service;

import controller.StudentTestControllerImpl;
import dao.TestQuestionDaoImpl;
import dto.TestQuestion;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.interfaces.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
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

    @Autowired
    public TestImpl(StudentTestControllerImpl controller, TestQuestionDaoImpl questionDao,
                    MessageImpl messageSourceService, PrintImpl printService) {
        this.controller = controller;
        this.questionDao = questionDao;
        this.messageSourceService = messageSourceService;
        this.printService = printService;
    }

    @Override
    public void init(PrintStream out, InputStreamReader in) {
        messageSourceService.init();
        printService.init(out, in);
    }

    public void startTest(int size) {
        if (size == 0) {
            printService.printLine(messageSourceService.getErrorMessage());
            return;
        }
        printService.printLine(messageSourceService.getHelloMessage(size));
    }

    //access to DAO
    public Set<TestQuestion> getDao() {
        return questionDao.getQuestions(messageSourceService.getFileName());
    }

    @Override
    public void test(PrintStream out, InputStreamReader in) {
        init(out, in);
        Set<TestQuestion> testQuestions = getDao();
        int numOfQuestions = testQuestions.size();
        int numOfRightAnswers = 0;
        startTest(numOfQuestions);
        controller.startTest(in);
        for (TestQuestion question : testQuestions) {
            String answer = controller.test(question.getQuestion());
            if (question.getRightAnswer().equals(answer.trim())) {
                numOfRightAnswers++;
            }
        }

        if (numOfRightAnswers == 0) {
            printService.printLine(messageSourceService.getResultMessage(false, numOfRightAnswers, numOfQuestions));
        } else {
            printService.printLine(messageSourceService.getResultMessage(true, numOfRightAnswers, numOfQuestions));
        }
    }

}
