package blackjack.domain.gameresult;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static blackjack.domain.gameresult.Result.*;
import static org.assertj.core.api.Assertions.assertThat;

class ProfitRateTest {

    @DisplayName("블랙잭 승리: 배팅액의 1.5배 수익 반환")
    @Test
    void should_return1_5timesBatting_When_BlackJack_Win() {
        double profit = ProfitRate.calculateProfit(BLACKJACK_WIN, 100.0);
        assertThat(profit).isEqualTo(100.0 * 1.5);
    }

    @DisplayName("승리: 배팅액의 1배 수익 반환")
    @Test
    void should_returnOnetimesBatting_When_Win() {
        double profit = ProfitRate.calculateProfit(WIN, 100.0);
        assertThat(profit).isEqualTo(100.0);
    }

    @DisplayName("패배: 배팅액의 손실")
    @Test
    void should_returnMinusOnetimesBatting_When_Lose() {
        double profit = ProfitRate.calculateProfit(LOSE, 100.0);
        assertThat(profit).isEqualTo(100.0 * -1.0);
    }

    @DisplayName("무승부 : 배팅액 회수")
    @Test
    void should_returnZero_When_Draw() {
        double profit = ProfitRate.calculateProfit(DRAW, 100.0);
        assertThat(profit).isZero();
    }
}
