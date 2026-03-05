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

        public static final String DELIMITER = ",";

        @Test
        @DisplayName("사용자 이름 입력 값이 구분자를 통해 정상 분리")
        void 사용자_이름_입력값_분리_정상_테스트() {
            String input = "pobi,jason";

            List<String> usernames = List.of(input.split(DELIMITER));

            List<String> expect = List.of("pobi", "jason");
            assertThat(usernames).isEqualTo(expect);
        }

        @Test
        @DisplayName("사용자 이름 입력 값이 공백인 경우 구분자 분리 실패")
        void 사용자_이름_입력값_실패_테스트() {
            String input = "";

            assertThatIllegalArgumentException()
                    .isThrownBy(() -> {
                        if (input.isEmpty())
                            throw new IllegalArgumentException(ErrorMessage.EMPTY_INPUT.getMessage());
                    })
                    .withMessage(ErrorMessage.EMPTY_INPUT.getMessage());
        }
    }
}

