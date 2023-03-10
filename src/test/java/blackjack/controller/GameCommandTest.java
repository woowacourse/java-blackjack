package blackjack.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.controller.exception.InvalidCommandException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class GameCommandTest {

    @Test
    @DisplayName("올바른 입력값이 아니면 예외가 발생한다")
    void wrongInputTest() {
        assertThatThrownBy(() -> GameCommand.from("a"))
                .isInstanceOf(InvalidCommandException.class);
    }

    @ParameterizedTest
    @CsvSource(value = {"y:PLAY", "n:STOP"}, delimiter = ':')
    @DisplayName("입력값에 따라 커맨드를 반환한다")
    void createCommandTest(String input, GameCommand expect) {
        assertThat(GameCommand.from(input)).isEqualTo(expect);
    }
}
