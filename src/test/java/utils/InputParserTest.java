package utils;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import meesage.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class InputParserTest {

    @Nested
    @DisplayName("사용자 입력 값 구분자(',') 분리 검증")
    class SplitByDelimiter {

        @Test
        @DisplayName("사용자 이름 입력 값이 구분자를 통해 정상 분리")
        void user_name_split_success() {
            String input = "pobi,jason";

            assertThat(InputParser.splitByDelimiter(input)).isEqualTo(List.of("pobi", "jason"));
        }

        @Test
        @DisplayName("사용자 이름 입력 값이 공백인 경우 구분자 분리 실패")
        void user_name_isEmpty_failed() {
            String input = "";

            assertThatIllegalArgumentException()
                    .isThrownBy(() -> InputParser.splitByDelimiter(input))
                    .withMessage(ErrorMessage.EMPTY_INPUT.getMessage());
        }

        @ParameterizedTest(name = "[{index}] input=\"{0}\" → 예외 발생")
        @ValueSource(strings = {" ", "!!", "blackjack", "001", "-1", "1 1"})
        @DisplayName("입력 값이 공백, 문자, 0으로 시작하는 숫자, 음수인 경우 예외를 던진다")
        void not_numeric_pattern_throw_exception(String input) {
            assertThatIllegalArgumentException()
                    .isThrownBy(() -> InputParser.parseIntStrict(input))
                    .withMessageContaining(ErrorMessage.NOT_STRICT_NUMERIC.getMessage());
        }

    }
}

