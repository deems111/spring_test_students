package service;

import controller.StudentTestControllerImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import testConfig.TestConfig;

import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
public class TestImplTest {

    @Autowired
    private TestImpl test;
    @Autowired
    private StudentTestControllerImpl controller;

    @Test
    public void testTest() {
        ByteArrayOutputStream stream = changeSystemOut();

        when(controller.test(anyString()))
                .thenReturn("1024")
                .thenReturn("255")
                .thenReturn("15");

        test.test(System.out, new InputStreamReader(System.in));

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

