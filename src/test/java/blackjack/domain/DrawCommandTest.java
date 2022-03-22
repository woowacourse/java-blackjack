package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class DrawCommandTest {

    @ParameterizedTest
    @DisplayName("n,y 를 입력받았을 때 예상값을 반환하는지 그리고 이외에 값에 대한 예외처리")
    @MethodSource("drawCommandInputProvide")
    void drawCommand_inputValue(String inputValue, DrawCommand expectedDrawCommand) {
        try {
            DrawCommand drawCommand = DrawCommand.from(inputValue);
            assertThat(drawCommand).isEqualTo(expectedDrawCommand);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(IllegalArgumentException.class);
        }

    }

    public static Stream<Arguments> drawCommandInputProvide() {
        return Stream.of(
                Arguments.arguments("y", DrawCommand.YES),
                Arguments.arguments("n", DrawCommand.NO),
                Arguments.arguments("x", null)
        );
    }

    @Test
    @DisplayName("입력값이 n 과 y가 아닐 때 예외처리")
    void drawCommand_exception() {
        String inputValue = "x";
        assertThatThrownBy(() -> DrawCommand.from(inputValue)).isInstanceOf(IllegalArgumentException.class);
    }
}
