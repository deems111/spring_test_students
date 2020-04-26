package dao;

import dao.interfaces.TestQuestionDao;
import dto.TestQuestion;
import lombok.*;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/**
 * Implementation of TestQuestionDao
 */
@AllArgsConstructor
@Getter
@Setter
@Repository
public class TestQuestionDaoImpl implements TestQuestionDao {

    //getting set of questions
    @Override
    public Set<TestQuestion> getQuestions(String fileName) {
        Set<TestQuestion> questions = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(ClassLoader.getSystemClassLoader().getResourceAsStream(fileName)))) {
            while (reader.ready()) {
                TestQuestion question = new TestQuestion();
                String[] readStringArr = reader.readLine().split(";");

                if (readStringArr.length == 2) {
                    question.setQuestion(readStringArr[0]);
                    question.setRightAnswer(readStringArr[1]);
                    questions.add(question);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new HashSet<>();
        }
        return questions;
    }
}
