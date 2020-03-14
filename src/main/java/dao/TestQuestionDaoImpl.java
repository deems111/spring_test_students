package dao;

import dao.interfaces.TestQuestionDao;
import dto.TestQuestion;
import lombok.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/**
 * Implementation of TestQuestionDao
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class TestQuestionDaoImpl implements TestQuestionDao {

    private String fileName;

    //getting set of questions
    @Override
    public Set<TestQuestion> getQuestions() {
        Set<TestQuestion> questions = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(ClassLoader.getSystemClassLoader().getResourceAsStream(fileName)))) {
            while (reader.ready()) {
                TestQuestion question = new TestQuestion();
                String [] readedStringArr = reader.readLine().split(";");

                if (readedStringArr.length ==2) {
                    question.setQuestion(readedStringArr[0]);
                    question.setRightAnswer(readedStringArr[1]);
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
