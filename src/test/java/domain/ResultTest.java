package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ResultTest {

    @DisplayName("승에 해당하는 경우 배팅 금액의 1배를 반환한다.")
    @Test
    void 승리_베팅금액_반환() {
        Result result = Result.WIN;
        assertThat(result.calculateWinningAmount(1_000)).isEqualTo(1_000);
    }

    @DisplayName("패에 해당하는 경우 배팅 금액의 1배를 반환한다.")
    @Test
    void 패배_베팅금액_반환() {
        Result result = Result.LOSE;
        assertThat(result.calculateWinningAmount(1_000)).isEqualTo(-1_000);
    }

    @DisplayName("무승부에 해당하는 경우 배팅 금액의 1배를 반환한다.")
    @Test
    void 무승부_베팅금액_반환() {
        Result result = Result.DRAW;
        assertThat(result.calculateWinningAmount(1_000)).isEqualTo(0);
    }

    @DisplayName("블랙잭에 해당하는 경우 배팅 금액의 1배를 반환한다.")
    @Test
    void 블랙잭_베팅금액_반환() {
        Result result = Result.BLACKJACK;
        assertThat(result.calculateWinningAmount(1_000)).isEqualTo(1_500);
    }
}