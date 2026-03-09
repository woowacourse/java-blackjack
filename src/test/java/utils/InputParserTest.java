package utils;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import meesage.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class InputParserTest {

    @Nested
    @DisplayName("사용자 입력 값 구분자(',') 분리 검증")
    class SplitByDelimiter {

        private static final String DELIMITER = ",";

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
    }
}

