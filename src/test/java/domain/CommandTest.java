package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.command.DrawCommand;
import domain.message.ExceptionMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CommandTest {

    @DisplayName("입력 값이 정상적인 경우 객체를 생성한다.")
    @ParameterizedTest
    @ValueSource(strings = {"y", "n"})
    void create_success(final String command) {
        // when & then
        assertThatNoException()
                .isThrownBy(() -> new DrawCommand(command));
    }

    @DisplayName("입력 값이 정상적이지 않은 경우 예외를 발생시킨다.")
    @ParameterizedTest
    @ValueSource(strings = {"a", "b", " "})
    void create_fail(final String command) {
        // when & then
        assertThatThrownBy(() -> new DrawCommand(command))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMessage.COMMAND_NOT_PERMITTED.getMessage());
    }

    @DisplayName("사용자가 뽑는 커맨드를 입력하면 true를 반환한다.")
    @Test
    void returns_true_when_input_is_draw_command() {
        // given
        String givenInput = "y";
        DrawCommand drawCommand = new DrawCommand(givenInput);

        // when
        boolean expectedResult = drawCommand.isDraw();

        // then
        assertThat(expectedResult).isTrue();
    }

    @DisplayName("사용자가 멈추는 커맨드를 입력하면 true 반환한다.")
    @Test
    void returns_true_when_input_is_stop_command() {
        // given
        String givenInput = "n";
        DrawCommand drawCommand = new DrawCommand(givenInput);

        // when
        boolean expectedResult = drawCommand.isStop();

        // then
        assertThat(expectedResult).isTrue();
    }
}
