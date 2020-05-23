package controller;

import controller.interfaces.StudentsTestController;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * Implementation of StudentsTestController
 */
@Controller
@Setter
public class StudentTestControllerImpl implements StudentsTestController {

    private final InputStream in;
    private BufferedReader reader;

    @Autowired
    public StudentTestControllerImpl(InputStream in) {
        this.in = in;
    }

    @Override
    public void endTest() {
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void startTest() {
        this.reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
    }

    @Override
    public String test(String testQuestion) {
        if (reader == null) {
            startTest();
        }

        String answer = "";
        try {
            System.out.println(testQuestion + "?");
            answer = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return answer;
    }

}
