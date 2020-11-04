package service;

import configuration.LanguageConfig;
import controller.StudentTestControllerImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import testConfig.TestConfig;

import java.io.*;
import java.util.Locale;
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
    @Autowired
    private LanguageConfig languageConfig;

    private static final String TEST_FILE_PATH = "C://Users//user//1.txt";

    @Test
    public void testTest() throws Exception {
        languageConfig.setLocale(Locale.ENGLISH);
        languageConfig.setFileName("questions/questions_en_US");
        controller.setReader(null);
        when(controller.test(anyString()))
                .thenReturn("1024")
                .thenReturn("255")
                .thenReturn("15");
        System.setOut(new PrintStream(new File(TEST_FILE_PATH)));
        test.test(System.out, System.in);

        StringBuilder expected = new StringBuilder("Test for student")
                .append("Test consists of 3 question(s)")
                .append("Answer should consist only of numbers")
                .append("Test completed")
                .append("Test passed")
                .append("Number of correct answers - 3 from 3");
        Assert.assertTrue(getResultString().contains(expected.toString()));

        System.setOut(System.out);
    }

    private String getResultString() throws Exception {
        StringBuilder builder = new StringBuilder();

        FileReader reader = new FileReader(TEST_FILE_PATH);
        Scanner scanner = new Scanner(reader);
        while (scanner.hasNextLine()) {
            builder.append(scanner.nextLine());
        }

        reader.close();
        return builder.toString().replaceAll("(\r\n|\n)", "");
    }
}

