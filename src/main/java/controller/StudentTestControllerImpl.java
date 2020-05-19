package controller;

import controller.interfaces.StudentsTestController;
import org.springframework.stereotype.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Implementation of StudentsTestController
 */
@Controller
public class StudentTestControllerImpl implements StudentsTestController {

    BufferedReader reader;

    @Override
    public void startTest(InputStreamReader streamReader) {
        this.reader = new BufferedReader(streamReader);
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
            startTest(new InputStreamReader(System.in));
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
