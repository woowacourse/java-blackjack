package blackjack.view;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CommandTest {

    @Test
    @DisplayName("y를 입력하면 HIT 커맨드를 얻는다.")
    void from_WhenCommandIsY() {
        assertTrue(Command.from("y").isHit());
    }

    @Test
    @DisplayName("n을 입력하면 STAND 커맨드를 얻는다.")
    void from_WhenCommandIsN() {
        assertThat(Command.from("n")).isEqualTo(Command.STAND);
    }

    @Test
    @DisplayName("y, n 이외의 값을 입력하면 예외가 발생한다.")
    void from_WhenCommandIsNotYorN() {
        assertThatThrownBy(() -> Command.from("a"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("y또는 n만 입력 가능합니다.");
    }
}
