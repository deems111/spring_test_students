package dao.interfaces;

import dto.TestQuestion;

import java.util.Set;

/**
 * Base interface for test questions
 */
public interface TestQuestionDao {

    Set<TestQuestion> getQuestions ();
}
