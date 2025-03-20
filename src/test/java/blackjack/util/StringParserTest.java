package blackjack.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StringParserTest {

    @DisplayName("문자열을 쉼표로 파싱한다.")
    @Test
    void parseByComma() {
        // given
        final String input = "엠제이, 밍트";
        final List<String> expected = List.of("엠제이", "밍트");

        // when & then
        assertThat(StringParser.parseByComma(input)).isEqualTo(expected);
    }

    @Test
    @DisplayName("문자열을 숫자로 파싱한다.")
    void parseToInt() {
        // given
        final String input = "11";
        final int expected = 11;

        // when & then
        assertThat(StringParser.parseInt(input)).isEqualTo(expected);
    }

    @Test
    @DisplayName("숫자가 아닌 문자열을 파싱할 경우 예외가 발생한다.")
    void parseToIntWithNotNumber() {
        // given
        final String input = "11.3";

        // when & then
        Assertions.assertThatThrownBy(() -> StringParser.parseInt(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 11.3은 숫자 형식이 아닙니다.");
    }

}
