package blackjack.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StringParserTest {

    @DisplayName("문자열을 쉼표로 파싱한다.")
    @Test
    void test() {
        // given
        final String input = "엠제이,밍트";
        final List<String> expected = List.of("엠제이", "밍트");

        // when & then
        assertThat(StringParser.parseComma(input)).isEqualTo(expected);
    }
}
