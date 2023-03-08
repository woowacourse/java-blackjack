package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.NoSuchElementException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerCommandTest {

    @DisplayName("y를 입력하면 HIT으로 변환할 수 있다.")
    @Test
    void convertHitCommandTest() {
        assertThat(PlayerCommand.from("y")).isEqualTo(PlayerCommand.HIT);
    }

    @DisplayName("n을 입력하면 STAND로 변환할 수 있다.")
    @Test
    void convertStandCommandTest() {
        assertThat(PlayerCommand.from("n")).isEqualTo(PlayerCommand.STAND);
    }

    @DisplayName("y나 n이 아닌 커맨드를 입력하면 예외 처리한다.")
    @Test
    void convertInvalidCommandTest() {
        String command = "a";
        assertThatThrownBy(() -> PlayerCommand.from(command))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessageContaining(String.format(PlayerCommand.COMMAND_ERROR_MESSAGE, command));
    }
}
