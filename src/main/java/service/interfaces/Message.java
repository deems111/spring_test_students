package service.interfaces;

public interface Message {

    void init();

    String getMessage(String textBundle);

    String getFileName();

    String getHelloMessage(int size);

    String getErrorMessage();

    String getResultMessage(boolean passed, int numOfRightAnswers, int numOfQuestions);
}
