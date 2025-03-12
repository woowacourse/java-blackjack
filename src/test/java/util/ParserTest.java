package util;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParserTest {
    @Test
    @DisplayName("주어진 구분자로 문자열을 나누는지 확인합니다.")
    void splitByDelimiterTest() {
        //given
        String rawInput = "1,2,3 , 4";
        List<String> answer = List.of("1", "2", "3", "4");
        // when
        List<String> result = Parser.splitByDelimiter(rawInput, ",");
        // then
        assertThat(result).isEqualTo(answer);
    }
}