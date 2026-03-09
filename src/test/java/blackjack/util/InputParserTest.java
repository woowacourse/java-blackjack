package blackjack.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InputParserTest {

    @Test
    @DisplayName("쉼표를 기준으로 문자열 분리")
    void test_parse_input() {
        String input = "pobi,james";

        List<String> result = InputParser.parse(input);
        assertThat(result).containsExactly("pobi", "james");
    }
}
