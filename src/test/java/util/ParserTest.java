package util;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.Parser;

class ParserTest {
    @Test
    @DisplayName("게임 참가자 이름 문자열 구분자 기준 분리 테스트")
    void 게임_참가자_이름_문자열_분리_테스트() {
        // when
        List<String> names = Parser.parse("pobi, jason", ",");
        // then
        Assertions.assertThat(names).isEqualTo(List.of("pobi", "jason"));
    }
}
