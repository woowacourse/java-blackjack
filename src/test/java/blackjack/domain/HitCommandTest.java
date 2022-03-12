package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class HitCommandTest {

    @ParameterizedTest
    @DisplayName("없는 커맨드가 들어올 경우 예외를 발생시킨다.")
    @ValueSource(strings = {"a", "yes", "1"})
    void fromException(String input) {
        assertThatThrownBy(() -> HitCommand.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("없는 커맨드입니다.");
    }

    @Test
    @DisplayName("null이 들어올 경우 예외를 발생시킨다.")
    void fromExceptionByNull() {
        assertThatThrownBy(() -> HitCommand.from(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("커맨드에는 null이 들어올 수 없습니다.");
    }

    @ParameterizedTest
    @DisplayName("정상적으로 커맨드를 반환한다.")
    @CsvSource({"y, YES", "n, NO"})
    void from(String input, HitCommand expected) {
        assertThat(HitCommand.from(input)).isEqualTo(expected);
    }
}