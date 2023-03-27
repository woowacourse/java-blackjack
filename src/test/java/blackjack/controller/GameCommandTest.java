package blackjack.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static blackjack.controller.GameCommand.from;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class GameCommandTest {

    @ParameterizedTest
    @CsvSource(value = {"y:PLAY", "n:STOP"}, delimiter = ':')
    @DisplayName("입력값에 따라 커맨드를 반환한다")
    void createCommandTest(String input, GameCommand expect) {
        assertThat(from(input)).isEqualTo(expect);
    }

    @Test
    @DisplayName("올바른 입력값이 아니면 예외가 발생한다")
    void wrongInputTest() {
        assertThatThrownBy(() -> from("a"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("y 또는 n을 입력해주세요.");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("입력값이 빈 값이면 예외가 발생한다")
    void nullOrEmptyInputTest(String input) {
        assertThatThrownBy(() -> from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("y 또는 n을 입력해주세요.");
    }

    @Test
    @DisplayName("입력값이 y라면 play인지 확인시 true를 반환한다")
    void isPlayTest() {
        GameCommand gameCommand = from("y");

        Assertions.assertTrue(gameCommand.isPlay());
    }
}
