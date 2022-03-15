package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {

    @DisplayName("null 또는 빈 값을 입력했을 때 예외 발생을 확인한다.")
    @ParameterizedTest
    @NullAndEmptySource
    void isGiven_null_or_empty_exception(String input) {
        assertThatThrownBy(() -> HitOption.hits(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("빈 값을 입력할 수 없습니다.");
    }

    @DisplayName("y, Y를 입력 받았을 때 True 를 반환하는지 확인한다.")
    @ParameterizedTest
    @ValueSource(strings = {"y", "Y"})
    void isGiven_true(String input) {
        final boolean hitOption = HitOption.hits(input);

        assertThat(hitOption).isTrue();
    }

    @DisplayName("n, N를 입력 받았을 때 False 를 반환하는지 확인한다.")
    @ParameterizedTest
    @ValueSource(strings = {"n", "N"})
    void isGiven_false(String input) {
        final boolean hitOption = HitOption.hits(input);

        assertThat(hitOption).isFalse();
    }

    @DisplayName("y, n 외의 값을 입력할 경우 예외를 발생시킨다.")
    @Test
    void isGiven_exception() {
        assertThatThrownBy(() -> HitOption.hits("a"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("y, n 중에서 입력해주세요.");
    }
}
