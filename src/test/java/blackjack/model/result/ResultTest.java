package blackjack.model.result;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.results.Result;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ResultTest {
    @DisplayName("블랙잭으로 이긴 경우 베팅 금액의 1.5배를 받는다")
    @Test
    void winByBlackJack() {
        Result result = Result.WIN_BY_BLACKJACK;
        int betAmount = 1000;
        int profit = result.getProfit(betAmount);

        assertThat(profit).isEqualTo(1500);
    }

    @DisplayName("블랙잭이 아니면서 이긴 경우 베팅 금액의 1배를 받는다")
    @Test
    void win() {
        Result result = Result.WIN;
        int betAmount = 1000;
        int profit = result.getProfit(betAmount);

        assertThat(profit).isEqualTo(1000);
    }

    @DisplayName("비긴경우 베팅한 금액을 돌려받는다")
    @Test
    void push() {
        Result result = Result.PUSH;
        int betAmount = 1000;
        int profit = result.getProfit(betAmount);

        assertThat(profit).isEqualTo(0);
    }

    @DisplayName("진 경우 베팅한 금액만큼을 잃는다")
    @Test
    void lose() {
        Result result = Result.LOSE;
        int betAmount = 1000;
        int profit = result.getProfit(betAmount);

        assertThat(profit).isEqualTo(-1000);
    }

    @DisplayName("블랙잭으로 진 경우 딜러는 베팅 금액의 1.5배를 잃는다")
    @Test
    void loseByBlackJack() {
        Result result = Result.LOSE_BY_BLACKJACK;
        int betAmount = 1000;
        int profit = result.getProfit(betAmount);

        assertThat(profit).isEqualTo(-1500);
    }
}
