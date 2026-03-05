package util;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

public class ParserTest {
    public static final String ERROR_PREFIX = "[ERROR] ";

    @Test
    void 빈_참가자_이름_예외_테스트() {
        // given
        String participantsName = "";

        // when & then
        assertThatThrownBy(() ->
                Parser.validateNonLiteralInput(participantsName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith(ERROR_PREFIX);
    }

    @Test
    void 참가자_이름_숫자_예외_테스트() {
        // given
        String participantsName = "123";

        // when & then
        assertThatThrownBy(() ->
                Parser.validateNonLiteralInput(participantsName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith(ERROR_PREFIX);
    }

    @Test
    void 쉼표_외의_특수문자_예외_테스트() {
        // given
        String participantsName = "영기:라이";

        // when & then
        assertThatThrownBy(() ->
                Parser.validateNonLiteralInput(participantsName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith(ERROR_PREFIX);
    }
}
