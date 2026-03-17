package util;

import static message.ErrorMessage.BETTING_MONEY_NOT_AVAILABLE;

import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

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
        List<String> playerNames = InputParser.parseNames(input);

        Assertions.assertThat(playerNames).isEqualTo(expectedNames);
    }

    private static Stream<Arguments> inputMoney() {
        return Stream.of(
                Arguments.of("10000", 10000),
                Arguments.of("0", 0),
                Arguments.of("-10000", -10000)
        );
    }

    @DisplayName("String으로 된 숫자를 int 숫자로 파싱한다.")
    @ParameterizedTest
    @MethodSource("inputMoney")
    void 정수형_숫자로_파싱한다(String input, int expectedValue) {
        //given
        long money = InputParser.parseMoney(input);
        //when
        //then
        Assertions.assertThat(money).isEqualTo(expectedValue);
    }

    @DisplayName("정수형 숫자가 아닌 값이 입력되면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"10000.12312", "stark", "스타크", ",./1+_)("})
    void 정수_아닐_경우_예외_발생한다(String input) {
        Assertions.assertThatThrownBy(() -> InputParser.parseMoney(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(BETTING_MONEY_NOT_AVAILABLE.getMessage());
    }
}
