package model;

import exception.IllegalYesOrNoInputException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class YesOrNoTest {
    @ParameterizedTest
    @DisplayName("입력값이 Y, y, N, n가 아닐 때 검증 테스트")
    @ValueSource(strings = {"q", ","})
    void input_Validate_Test(String input) {
        assertThatThrownBy(() -> YesOrNo.findAnswer(input))
                .isInstanceOf(IllegalYesOrNoInputException.class);
    }

    @ParameterizedTest
    @DisplayName("입력값이 N, n 일 때 테스트")
    @ValueSource(strings = {"N", "n"})
    void input_No_Test(String input) {
        assertThat(YesOrNo.findAnswer(input).isYes()).isFalse();
    }

    @ParameterizedTest
    @DisplayName("입력값이 Y, y 일 때 테스트")
    @ValueSource(strings = {"Y", "y"})
    void input_Yes_Test(String input) {
        assertThat(YesOrNo.findAnswer(input).isYes()).isTrue();
    }
}
