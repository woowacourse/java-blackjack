package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AnswerTest {

    @DisplayName("y, Y를 입력 받았을 때 True 를 반환하는지 확인한다.")
    @ParameterizedTest
    @ValueSource(strings = {"y", "Y"})
    void is_draw_true(String input) {
        assertThat(Answer.isDraw(input)).isTrue();
    }

    @DisplayName("n, N를 입력 받았을 때 False 를 반환하는지 확인한다.")
    @ParameterizedTest
    @ValueSource(strings = {"n", "N"})
    void is_draw_false(String input) {
        assertThat(Answer.isDraw(input)).isFalse();
    }

    @DisplayName("y, n 외의 값을 입력할 경우 예외를 발생시킨다.")
    @Test
    void is_draw_exception() {
        assertThatThrownBy(() -> Answer.isDraw("a"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("y, n 중에서 입력해주세요.");
    }
}