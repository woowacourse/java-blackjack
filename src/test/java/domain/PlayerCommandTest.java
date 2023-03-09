package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.NoSuchElementException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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

    @DisplayName("enum 값이 HIT이면 true, STAND면 false를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"HIT,true", "STAND,false"}, delimiter = ',')
    void isHitTest(PlayerCommand command, boolean expected) {
        assertThat(command.isHit()).isEqualTo(expected);
    }
}
