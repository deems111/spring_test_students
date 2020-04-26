import controller.StudentTestControllerImpl;
import dao.TestQuestionDaoImpl;
import dto.TestQuestion;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import service.TestImpl;
import testConfig.TestConfig;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Locale;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
public class Tester {

    @Autowired
    private TestImpl test;
    @Autowired
    private StudentTestControllerImpl controller;
    @Autowired
    private BufferedReader reader;
    @Autowired
    private MessageSource messageSource;

    @Test
    public void testLocale() {
        test.setLocale(Locale.ENGLISH);
        Set<TestQuestion> expected = new TestQuestionDaoImpl().getQuestions("questions.csv");
        Assert.assertTrue(expected.containsAll(test.getDao()));
    }

    @Test
    public void testDao() {
        Set<TestQuestion> daoQuestions = test.getDao();
        Set<TestQuestion> expected = new TestQuestionDaoImpl().getQuestions("questions_ru.csv");

        Assert.assertEquals(daoQuestions.size(), 3);
        Assert.assertEquals(daoQuestions.size(), expected.size());
        Assert.assertTrue(expected.containsAll(daoQuestions));
    }

    @Test
    public void testWholeTest() {
        ByteArrayOutputStream stream = changeSystemOut();

        when(controller.test(anyString()))
                .thenReturn("1024")
                .thenReturn("255")
                .thenReturn("15");

        test.test();

        StringBuilder expected =  new StringBuilder("Test for student")
                .append("Test consists of 3 question(s)")
                .append("Answer should consist only of numbers")
                .append("Test completed")
                .append("Test passed")
                .append("Number of correct answers - 3 from 3");

        Assert.assertEquals(stream.toString().replaceAll("(\r\n|\n)", ""),  expected.toString());
    }

    /**
     * Change System.Out for Mockito
     */
    private ByteArrayOutputStream changeSystemOut() {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(stream));
        return stream;
    }
}
