package controller;

import controller.interfaces.StudentsTestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Implementation of StudentsTestController
 */
@Controller
public class StudentTestControllerImpl implements StudentsTestController {

    @Autowired
    private BufferedReader reader;

    @Override
    public void startTest() {
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
