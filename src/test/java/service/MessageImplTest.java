package service;

import controller.StudentTestControllerImpl;
import dao.TestQuestionDaoImpl;
import dto.TestQuestion;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import testConfig.TestConfig;

import java.util.Locale;
import java.util.Set;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
public class MessageImplTest {

    @Autowired
    private TestImpl test;
    @Autowired
    private StudentTestControllerImpl controller;
    @Autowired
    private MessageImpl message;

    @Test
    public void testLocale() {
        message.setLocale(Locale.ENGLISH);
        Set<TestQuestion> expected = new TestQuestionDaoImpl().getQuestions("questions.csv");
        Assert.assertTrue(expected.containsAll(test.getDao()));
    }

    @Test
    public void testGetMessage() {
        String result = message.getMessage("test.hello");
        String expected = "Test for student";
        Assert.assertTrue(result.equalsIgnoreCase(expected));
    }
}
