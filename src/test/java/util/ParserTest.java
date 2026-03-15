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

            // when
            List<String> actual = Parser.parseInput(input);

            // then
            List<String> expected = List.of("a", "b", "c");
            for (int i = 0; i < 3; i++) {
                Assertions.assertEquals(expected.get(i), actual.get(i));
            }
        }

        @Test
        void 파싱한_입력값_앞뒤에_공백이_있다면_제거해야_한다() {

            // given
            String input = "    a ,b ,c  ";

            // when
            List<String> actual = Parser.parseInput(input);

            // then
            List<String> expected = List.of("a", "b", "c");
            for (int i = 0; i < 3; i++) {
                Assertions.assertEquals(expected.get(i), actual.get(i));
            }
        }

        @Test
        void 파싱한_입력값_중간에_공백이_있다면_제거하지_않는다() {

            // given
            String input = "    a b ,b  c ,c   d ";

            // when
            List<String> actual = Parser.parseInput(input);

            // then
            List<String> expected = List.of("a b", "b  c", "c   d");
            for (int i = 0; i < 3; i++) {
                Assertions.assertEquals(expected.get(i), actual.get(i));
            }
        }
    }
}
