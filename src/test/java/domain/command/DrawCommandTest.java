package domain.command;

import static org.assertj.core.api.Assertions.assertThat;

import domain.command.DrawCommand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Named;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

@DisplayName("DrawCommand는 ")
class DrawCommandTest {
    @ParameterizedTest(name = "{0} 입력값이 들어오면 {1}")
    @MethodSource("createDrawCommandTestCase")
    void createDrawCommandTest(String input, DrawCommand expected) {
        DrawCommand drawCommand = DrawCommand.of(input);

        assertThat(drawCommand).isEqualTo(expected);
    }

    static Stream<Arguments> createDrawCommandTestCase() {
        return Stream.of(
                Arguments.of("y", Named.of("카드를 뽑는다.", DrawCommand.DRAW)),
                Arguments.of("n", Named.of("카드를 뽑지 않는다.", DrawCommand.STOP))
        );
    }
}
