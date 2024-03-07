package blackjack.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CommandTest {

    @Test
    @DisplayName("명령어를 올바르게 변환한다.")
    void convertCommandTest() {
        // when
        Command yes = Command.from("y");
        Command no = Command.from("n");
        // then
        assertAll(
                () -> assertThat(yes).isEqualTo(Command.YES),
                () -> assertThat(no).isEqualTo(Command.NO)
        );
    }

    @Test
    @DisplayName("존재하지 않는 명령어가 주어지면 예외를 발생시킨다.")
    void commandNotFoundTest() {
        assertThatThrownBy(() -> Command.from("hi"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 존재하지 않는 명령어입니다.");
    }
}