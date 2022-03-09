package blackjack.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CommandTest {

    @Test
    @DisplayName("입력값을 받아 Command를 반환한다. 입력값이 y라면 HIT을 반환한다.")
    void shouldReturnHitWhenY() {
        Command command = Command.of("y");
        Assertions.assertThat(command).isEqualTo(Command.HIT);
    }

    @Test
    @DisplayName("입력값을 받아 Command를 반환한다. 입력값이 y라면 HIT을 반환한다.")
    void shouldReturnStayWhenN() {
        Command command = Command.of("n");
        Assertions.assertThat(command).isEqualTo(Command.STAY);
    }

    @Test
    @DisplayName("입력값을 받아 Command를 반환한다. 존재하지 않는 심볼이라면 예외를 발생시킨다.")
    void shouldThrowException() {
        assertThatThrownBy(() -> Command.of("none"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
