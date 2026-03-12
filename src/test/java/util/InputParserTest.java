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
}