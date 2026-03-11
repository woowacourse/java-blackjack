package util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;


class InputParserTest {

    @Test
    @DisplayName("배팅 금액은 숫자여야 한다.")
    void bet_format_is_num() {
        String input = "금액";
        assertThatThrownBy(() -> InputParser.parseAmount(input))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("[ERROR] 배팅 금액은 숫자여야 합니다.");
    }
}
