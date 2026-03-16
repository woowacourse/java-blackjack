package blackjack.model.result;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerResultTest {

    @Test
    @DisplayName("결과에 따른 배율 계산 테스트")
    void getProfitMultipleTest() {
        // given
        PlayerResult win = PlayerResult.WIN;
        PlayerResult draw = PlayerResult.DRAW;
        PlayerResult lose = PlayerResult.LOSE;

        // when & then
        assertThat(win.getProfitMultiple(true)).isEqualTo(1.5);
        assertThat(win.getProfitMultiple(false)).isEqualTo(1);
        assertThat(draw.getProfitMultiple(false)).isEqualTo(0);
        assertThat(lose.getProfitMultiple(false)).isEqualTo(-1);
    }
}
