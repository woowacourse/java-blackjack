package util;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    void 빈_참가자_이름_예외_테스트() {
        // given
        String participantsName = "";

        // when & then
        assertThatThrownBy(() ->
                Parser.validateNonLiteralInput(participantsName))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 참가자_이름_숫자_예외_테스트()
}
