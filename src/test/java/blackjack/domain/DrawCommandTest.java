package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DrawCommandTest {

    @Test
    @DisplayName("y를 받았을 때 YES를 반환하는가?")
    void drawCommand_Y() {
        String inputValue = "y";
        DrawCommand drawCommand = DrawCommand.from(inputValue);
        assertThat(drawCommand).isEqualTo(DrawCommand.YES);
    }

    @Test
    @DisplayName("n를 받았을 때 YES를 반환하는가?")
    void drawCommand_N() {
        String inputValue = "n";
        DrawCommand drawCommand = DrawCommand.from(inputValue);
        assertThat(drawCommand).isEqualTo(DrawCommand.NO);
    }

    @Test
    @DisplayName("입력값이 n 과 y가 아닐 때 예외처리")
    void drawCommand_exception() {
        String inputValue = "x";
        assertThatThrownBy(() -> DrawCommand.from(inputValue)).isInstanceOf(IllegalArgumentException.class);
    }
}
