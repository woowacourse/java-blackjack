package util;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class InputParserTest {
    @Test
    void 이름_분리_테스트() {
        String input = "pobi, jason";

        List<String> result = InputParser.splitComma(input);
        Assertions.assertThat(result).hasSize(2).containsExactly("pobi", "jason");
    }

    @Test
    void 빈_문자열일때_예외(){
        String input = "";

        Assertions.assertThatThrownBy(() -> InputParser.splitComma(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 빈_문자열이_있을때_예외(){
        String input = "pobi,,jason";

        Assertions.assertThatThrownBy(() -> InputParser.splitComma(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 이름이_중복일때_예외(){
        String input = "pobi, pobi";

        Assertions.assertThatThrownBy(() -> InputParser.splitComma(input))
                .isInstanceOf(IllegalArgumentException.class);
    }
}