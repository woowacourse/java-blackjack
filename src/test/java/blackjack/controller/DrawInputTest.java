package blackjack.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DrawInputTest {

    @Test
    @DisplayName("y또는 n이외의 값을 입력시 예외처리하는지 테스트")
    void validateDrawInput() {
        assertThatThrownBy(() -> DrawInput.from("okay"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(DrawInput.INPUT_FORM_EXCEPTION_MESSAGE);
    }
}
