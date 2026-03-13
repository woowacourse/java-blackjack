package view;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class InputValidatorTest {
    @Test
    void 게임_진행_응답이_y_또는_n이_아니면_예외가_발생한다() {
        assertThrows(IllegalArgumentException.class, () -> {
            InputValidator.validateContinueResponse("dongkey");
        });
    }
}
