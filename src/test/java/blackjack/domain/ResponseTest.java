package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ResponseTest {

    @Test
    @DisplayName("입력에 따른 응답 처리 검증 및 예외처리")
    void getResponse() {
        assertThat(Response.getResponse("y")).isEqualTo(Response.POSITIVE);
        assertThat(Response.getResponse("Y")).isEqualTo(Response.POSITIVE);
        assertThat(Response.getResponse("n")).isEqualTo(Response.NEGATIVE);
        assertThat(Response.getResponse("N")).isEqualTo(Response.NEGATIVE);
        assertThatIllegalArgumentException().isThrownBy(() ->
            Response.getResponse("x")).withMessage("불가능한 입력 입니다.");
    }
}
