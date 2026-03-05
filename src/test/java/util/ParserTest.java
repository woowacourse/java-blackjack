package util;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class ParserTest {

    @Nested
    class ParseInputTest {
        
        @Test
        void 입력값이_구분자_기준으로_나누어져야_한다() {

            // given
            String input = "a,b,c";
            String delimiter = ",";

            // when
            List<String> actuals = Parser.parseInput(input, delimiter);

            // then
            List<String> expecteds = List.of("a", "b", "c");
            for (int i = 0; i < 3; i++) {
                Assertions.assertEquals(expecteds.get(i), actuals.get(i));
            }
        }

        @Test
        void 파싱한_입력값_앞뒤에_공백이_있다면_제거해야_한다() {

            // given
            String input = "    a ,b ,c  ";
            String delimiter = ",";

            // when
            List<String> actuals = Parser.parseInput(input, delimiter);

            // then
            List<String> expecteds = List.of("a", "b", "c");
            for (int i = 0; i < 3; i++) {
                Assertions.assertEquals(expecteds.get(i), actuals.get(i));
            }
        }
    }

}