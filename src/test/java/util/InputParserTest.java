package util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class InputParserTest {
    @ParameterizedTest(name = "{0}의 변환결과는 {1}이다")
    @DisplayName("정상적인 입력에 대한 테스트 케이스에 대해서는 정상적으로 동작한다.")
    @MethodSource("inputNameProvider")
    void testNameParseCase(String input, List<String> expected) {
        assertThat(InputParser.parsePlayerNames(input)).isEqualTo(expected);

    }

    static Stream<Arguments> inputNameProvider() {
        return Stream.of(
                Arguments.of("pobi, woni", List.of("pobi", "woni")),
                Arguments.of("jun, jason", List.of("jun", "jason"))
        );
    }
}
