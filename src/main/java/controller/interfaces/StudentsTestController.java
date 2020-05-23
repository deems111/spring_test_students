package controller.interfaces;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/**
 * Base interface for students testing
 */
public interface StudentsTestController {

    /**
     * Initialization
     */
    void startTest();

    /**
     * End - close resources
     */
    void endTest();

    /***
     * This method get @param testQuestion - question
     * @return String - answer
     */
    String test(String testQuestion) throws UnsupportedEncodingException;
}
