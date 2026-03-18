package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DrawCommandTest {

    @DisplayName("y를 입력하면 draw command를 생성한다.")
    @Test
    void createHitCommand() {
        // given
        String userCommand = "y";

        // when
        DrawCommand drawCommand = DrawCommand.from(userCommand);

        // then
        assertThat(drawCommand.isDraw()).isTrue();
    }

    @DisplayName("n을 입력하면 stand command를 생성한다.")
    @Test
    void createStandCommand() {
        // given
        String userCommand = "n";

        // when
        DrawCommand drawCommand = DrawCommand.from(userCommand);

        // then
        assertThat(drawCommand.isDraw()).isFalse();
    }

    @DisplayName("y 또는 n이 아닌 값을 입력하면 예외가 발생한다.")
    @Test
    void throwExceptionWhenCommandIsInvalid() {
        // given
        String invalidCommand = "boye";

        // when & then
        assertThatThrownBy(() -> DrawCommand.from(invalidCommand))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("유효한 커맨드(예는 y, 아니오는 n)를 입력해 주세요.");
    }
}
