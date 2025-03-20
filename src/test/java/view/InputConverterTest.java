package view;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import exception.IllegalBlackjackInputException;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class InputConverterTest {

    @DisplayName("구분자(,)를 기준으로 파싱한다.")
    @Test
    void splitTest() {
        String input = "pobi,jason,danny";
        List<String> expected = List.of("pobi", "jason", "danny");
        assertThat(InputConverter.splitByDelimiter(input, ","))
                .containsExactlyElementsOf(expected);
    }

    @DisplayName("숫자 문자열을 int로 변환한다.")
    @ParameterizedTest
    @CsvSource({
            "1000, 1000",
            "1500, 1500",
            "20000, 20000"
    })
    void parseToIntTest(final String input, final int expected) {
        assertThat(InputConverter.parseToInt(input)).isEqualTo(expected);
    }

    @DisplayName("숫자로만 이루어진 문자열이 아니라면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"1000원", "25000$", "25000won"})
    void validateParseToIntTest(final String input) {
        assertThatThrownBy(() -> InputConverter.parseToInt(input))
                .isInstanceOf(IllegalBlackjackInputException.class);
    }

    @DisplayName("입력이 null이나 blank라면 예외가 발생한다.")
    @ParameterizedTest
    @NullAndEmptySource
    void nullAndEmptyCheckTest(final String input) {
        assertThatThrownBy(() -> InputConverter.splitByDelimiter(input, ","))
                .isInstanceOf(IllegalBlackjackInputException.class);
    }
}
