package utils;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import blackjack.utils.Parser;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
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

}
