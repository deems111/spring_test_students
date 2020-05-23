package service;

import controller.StudentTestControllerImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import testConfig.TestConfig;

import java.io.*;
import java.util.Scanner;

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
    public void testTest() throws Exception {
        controller.setReader(null);
        when(controller.test(anyString()))
                .thenReturn("1024")
                .thenReturn("255")
                .thenReturn("15");
        test.test();

        StringBuilder expected = new StringBuilder("Test for student")
                .append("Test consists of 3 question(s)")
                .append("Answer should consist only of numbers")
                .append("Test completed")
                .append("Test passed")
                .append("Number of correct answers - 3 from 3");
        Assert.assertEquals(expected.toString(), getResultString());
    }

    private String getResultString() throws Exception {
        StringBuilder builder = new StringBuilder();

        FileReader reader = new FileReader("C://Users//dmitr//test.txt");
        Scanner scanner = new Scanner(reader);
        while (scanner.hasNextLine()) {
            builder.append(scanner.nextLine());
        }

        reader.close();
        return builder.toString().replaceAll("(\r\n|\n)", "");
    }
}

