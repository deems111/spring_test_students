package service;

import controller.StudentTestControllerImpl;
import dao.TestQuestionDaoImpl;
import dto.TestQuestion;
import lombok.*;
import service.interfaces.Test;

import java.util.Set;


/**
 * Implementation of Test for students testing
 */
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Data
public class TestImpl implements Test {

    private StudentTestControllerImpl controller;
    private TestQuestionDaoImpl questionDao;

    //before testing
    private void init(int size) {
        if(size == 0) {
            System.out.println("Error loading test for student");
            return;
        }
        System.out.println("Test for student");
        System.out.println("Test consists of " + size + "question(s)");
        System.out.println("Answer should consist only of numbers");
    }

    //access to DAO
    private Set<TestQuestion> getDao() {
        return questionDao.getQuestions();
    }

    @Override
    public void test() {
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

        System.out.println("Test completed");
        System.out.println("Number of correct answers - " + numOfRightAnswers + " from " + numOfQuestions + " answers");
    }
}
