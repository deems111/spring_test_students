package service;

import configuration.LanguageConfig;
import controller.StudentTestControllerImpl;
import dao.TestQuestionDaoImplTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import testConfig.TestConfig;

import java.util.Locale;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestQuestionDaoImplTest.class)
@Import({TestConfig.class})
public class MessageImplTest {

    @Autowired
    private TestImpl test;
    @Autowired
    private StudentTestControllerImpl controller;
    @Autowired
    private MessageImpl message;
    @Autowired
    private LanguageConfig languageConfig;

    @Test
    public void testGetMessage() {
        languageConfig.setLocale(Locale.ENGLISH);
        String result = message.getMessage("test.hello");
        String expected = "Test for student";
        Assert.assertTrue(result.equalsIgnoreCase(expected));
    }
}
