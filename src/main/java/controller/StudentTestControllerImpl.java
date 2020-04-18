package controller;

import controller.interfaces.StudentsTestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Implementation of StudentsTestController
 */
public class StudentTestControllerImpl implements StudentsTestController {

    private BufferedReader reader;

    @Override
    public void startTest() {
        reader = new BufferedReader(new InputStreamReader(System.in));
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
