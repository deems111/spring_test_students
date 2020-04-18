package dto;

import lombok.Data;

/**
 * DTO-entity realization of test question
 */
@Data
public class TestQuestion {

    private String question;
    private String rightAnswer;
}
