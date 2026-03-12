package domain.view;

import static message.ErrorMessage.BET_AMOUNT_INVALID_FORMAT;
import static message.ErrorMessage.BET_AMOUNT_OUT_OF_RANGE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import view.InputParser;

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

    @DisplayName("배팅 금액이 숫자가 아닌 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"a1234", "1,000", "피즈"})
    void 배팅_금액이_숫자가_아닌_경우_예외가_발생한다(String input) {
        assertThatThrownBy(() -> InputParser.parseBetAmount(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(BET_AMOUNT_INVALID_FORMAT.getMessage());
    }

    @DisplayName("배팅 금액이 int 범위를 초과한 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"1000000000000000000", "-1000000000000000000"})
    void 배팅_금액이_int_범위를_초과한_경우_예외가_발생한다(String input) {
        assertThatThrownBy(() -> InputParser.parseBetAmount(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(BET_AMOUNT_OUT_OF_RANGE.getMessage());
    }
}
