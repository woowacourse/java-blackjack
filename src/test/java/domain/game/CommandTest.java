package domain.game;

import static domain.game.Command.INVALID_COMMAND_MESSAGE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CommandTest {

    @Test
    @DisplayName("y또는 n이 아닐 경우 예외가 발생한다.")
    void createFail() {
        assertThatCode(() -> Command.from("yse"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_COMMAND_MESSAGE);
    }

    @Test
    @DisplayName("진행인지 멈추는지를 판단한다.")
    void isProceed() {
        Command proceedCommand = Command.from("y");
        Command stopCommand = Command.from("n");

        assertAll(
                () -> assertThat(proceedCommand.isProceed()).isTrue(),
                () -> assertThat(stopCommand.isProceed()).isFalse()
        );
    }
}