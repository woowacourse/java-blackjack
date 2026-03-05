package utils;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class InputParserTest {

    @Nested
    @DisplayName("사용자 입력 값 구분자(',') 분리")
    class SplitByDelimiter {

        public static final String DELIMITER = ",";

        @Test
        @DisplayName("사용자 이름 입력 값이 구분자를 통해 정상 분리")
        void 사용자_이름_입력값_정상_테스트() {
            String input = "pobi,jason";

            List<String> usernames = List.of(input.split(DELIMITER));

            List<String> expect = List.of("pobi", "jason");
            assertThat(usernames).isEqualTo(expect);
        }
    }
}

