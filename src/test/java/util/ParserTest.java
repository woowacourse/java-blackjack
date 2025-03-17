package util;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParserTest {
    @Test
    @DisplayName("주어진 구분자로 문자열을 나누는지 확인합니다.")
    void splitByDelimiterTest() {
        //given
        String rawInput = "1,2,3 , 4";
        List<String> answer = List.of("1", "2", "3", "4");

        // when & then
        Assertions.assertThat(Parser.splitByDelimiter(rawInput, ",")).isEqualTo(answer);
    }
}
