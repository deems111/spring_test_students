package dao;

import controller.StudentTestControllerImpl;
import dto.TestQuestion;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import service.TestImpl;
import testConfig.TestConfig;

import java.util.Set;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
public class TestQuestionDaoImplTest {

    @Autowired
    private TestImpl test;
    @Autowired
    private StudentTestControllerImpl controller;

    @Test
    public void testDao() {
        Set<TestQuestion> daoQuestions = test.getDao();
        Set<TestQuestion> expected = new TestQuestionDaoImpl().getQuestions("questions_ru.csv");

        Assert.assertEquals(daoQuestions.size(), 3);
        Assert.assertEquals(daoQuestions.size(), expected.size());
        Assert.assertTrue(expected.containsAll(daoQuestions));
    }

}
