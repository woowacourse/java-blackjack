package domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class BettingTest {

    @Test
    void 숫자_입력이_아니면_예외처리() {
        //given,when,then
        assertThatThrownBy(() -> new Betting("r"))
                .isInstanceOf(IllegalArgumentException.class);

    }

    @Test
    void 숫자_입력시_정상적으로_생성() {
        //given,when,then
        assertDoesNotThrow(() -> new Betting("1"));
    }
}
