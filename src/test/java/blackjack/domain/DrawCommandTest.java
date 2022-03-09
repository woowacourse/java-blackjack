package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class DrawCommandTest {

    @ParameterizedTest
    @DisplayName("없는 커맨드가 들어올 경우 예외를 발생시킨다.")
    @ValueSource(strings = {"a", "yes", "1"})
    void fromException(String input) {
        assertThatThrownBy(() -> DrawCommand.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("없는 커맨드입니다.");
    }
}