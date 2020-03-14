package dto;

import lombok.*;

/**
 * DTO-entity realization of test question
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TestQuestion {

    private String question;
    private String rightAnswer;
}
