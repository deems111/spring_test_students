package dao;

import configuration.LanguageConfigTest;
import controller.StudentTestControllerImpl;
import dto.TestQuestion;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import service.TestImpl;
import testConfig.TestConfig;

import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestQuestionDaoImplTest.class)
@Import({TestConfig.class})
public class TestQuestionDaoImplTest {

    @Autowired
    private TestImpl test;
    @Autowired
    private StudentTestControllerImpl controller;

    @Test
    public void getQuestionsTest() {
        Set<TestQuestion> daoQuestions = test.getDao();
        Set<TestQuestion> expected = new TestQuestionDaoImpl().getQuestions("questions/questions_en_US.csv");

        Assert.assertEquals(daoQuestions.size(), 3);
        Assert.assertEquals(daoQuestions.size(), expected.size());
        Assert.assertTrue(expected.containsAll(daoQuestions));
    }

}
