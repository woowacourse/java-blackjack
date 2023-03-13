package blackjack.domain;

import blackjack.utils.Command;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CommandTest {


    @DisplayName("올바른 명령어를 입력하지 않는 경우 에러를 발생시킨다.")
    @Test
    void Should_ThrowException_When_WrongInput() {
        assertThatThrownBy(() -> Command.isHit("a"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("올바른 명령어가 아닙니다.");
    }

    @DisplayName("y를 입력하는 경우 hit이 입력된 것으로 판단되어 true를 반환할 수 있다.")
    @Test
    void Should_Success_When_CorrectInput_y() {
        assertThat(Command.isHit("y")).isTrue();
    }

    @DisplayName("n을 입력하는 경우 hit이 아닌 걸로 판단되어 false를 반환할 수 있다.")
    @Test
    void Should_Success_When_CorrectInput_n() {
        assertThat(Command.isHit("n")).isFalse();
    }
}