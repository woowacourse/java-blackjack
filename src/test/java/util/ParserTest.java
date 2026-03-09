package util;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

public class ParserTest {
    Parser parser = new Parser();

    @Test
    void 참가자_이름_정상_파싱_테스트() {
        // given
        String request = "영기,라이";

        // when
        List<String> response = parser.parseParticipantsName(request);

        // then
        assertThat(response).isEqualTo(List.of("영기", "라이"));
    }
}
