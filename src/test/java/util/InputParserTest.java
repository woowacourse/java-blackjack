package util;

import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class InputParserTest {

    private static Stream<Arguments> inputNames() {
        return Stream.of(
                Arguments.of("pobi", List.of("pobi")),
                Arguments.of("pobi,jason", List.of("pobi", "jason")),
                Arguments.of("pobi,jason,stark", List.of("pobi", "jason", "stark")),
                Arguments.of("pobi,jason, stark", List.of("pobi", "jason", "stark")),
                Arguments.of("pobi,jason, stark, fizz ", List.of("pobi", "jason", "stark", "fizz"))
        );
    }

    @DisplayName("구분자(,)를 통해 플레이어 이름을 구분한다.")
    @ParameterizedTest
    @MethodSource("inputNames")
    void 구분자를_통해_플레이어_이름을_구분한다(String input, List<String> expectedNames) {
        List<String> playerNames =  InputParser.parseNames(input);

        Assertions.assertThat(playerNames).isEqualTo(expectedNames);
    }
}
