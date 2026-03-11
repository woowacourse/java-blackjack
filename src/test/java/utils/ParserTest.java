package utils;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import blackjack.utils.Parser;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class ParserTest {

    @Test
    @DisplayName("닉네임 입력 문자열 파싱 테스트")
    void 닉네임_입력_문자열_파싱_테스트() {
        String string = "pobi,jason";
        List<String> expected = List.of("pobi", "jason");

        List<String> actual = Parser.parseNickname(string);

        assertThat(actual).isEqualTo(expected);
    }

    @Nested
    @DisplayName("정수 파싱 테스트")
    class 정수_파싱_테스트 {

        @Test
        @DisplayName("정상 테스트")
        void 정상_테스트() {
            String rawString = "1";
            int expected = 1;

            int actual = Parser.parseInteger(rawString);

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        @DisplayName("정수가 아닌 값이 요청된 경우")
        void 정수가_아닌_값이_요청된_경우() {
            String wrongString = "a";

            assertThatThrownBy(() -> Parser.parseInteger(wrongString))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("정수만 입력 가능합니다.");
        }
    }


}
