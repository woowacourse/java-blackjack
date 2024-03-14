package model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OutcomeTest {

    @DisplayName("블랙잭인 경우 수익은 배팅금액의 1.5배를 반환한다.")
    @Test
    void calculateProfitIfBlackjack() {
        assertThat(Outcome.BLACKJACK.calculateProfit(100)).isEqualTo(150);
    }

    @DisplayName("무승부면 수익은 0원이다.")
    @Test
    void calculateProfitIfDraw() {
        assertThat(Outcome.DRAW.calculateProfit(100)).isEqualTo(0);
    }

    @DisplayName("승리인 경우 배팅금액의 1배를 반환한다.")
    @Test
    void calculateProfitIfWin() {
        assertThat(Outcome.WIN.calculateProfit(100)).isEqualTo(100);
    }

    @DisplayName("패배인 경우 배팅금액의 -1배를 반환한다.")
    @Test
    void calculateProfitIfLose() {
        assertThat(Outcome.LOSE.calculateProfit(100)).isEqualTo(-100);
    }
}
