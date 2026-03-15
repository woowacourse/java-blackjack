package blackjack.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InputParserTest {

    @Test
    @DisplayName("문자열을 정수로 파싱한다.")
    void parse() {
        assertThat(InputParser.parseInt("1000"))
                .isEqualTo(1000);
    }

    @Test
    @DisplayName("정수 형식이 아니면 예외가 발생한다.")
    void can_not_parse_non_number() {
        assertThatThrownBy(() -> InputParser.parseInt("not_number"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("정수가 아닙니다.");
    }
}