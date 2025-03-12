package blackjack.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BetAmountTest {
    @Test
    @DisplayName("겜블러가 패배했다면 배팅금액을 잃는다")
    void testBetAmount() {
        BetAmount betAmount = new BetAmount(10000);
        Profit profit = betAmount.getProfit(GameResult.LOSE);
        Assertions.assertThat(profit).isEqualTo(new Profit(-10000));
    }

    @Test
    @DisplayName("겜블러가 블랙잭이라면 배팅 금액의 1.5배를 받는다")
    void testBetAmount1() {
        BetAmount betAmount = new BetAmount(10000);
        Profit profit = betAmount.getProfit(GameResult.BLACKJACK);
        Assertions.assertThat(profit).isEqualTo(new Profit(10000 * 1.5));
    }

    @Test
    @DisplayName("겜블러가 승리하면 배팅 금액을 얻는다")
    void testBetAmount2() {
        BetAmount betAmount = new BetAmount(10000);
        Profit profit = betAmount.getProfit(GameResult.WIN);
        Assertions.assertThat(profit).isEqualTo(new Profit(10000));
    }

    @Test
    @DisplayName("비기면 수익이 없다")
    void testBetAmount3() {
        BetAmount betAmount = new BetAmount(10000);
        Profit profit = betAmount.getProfit(GameResult.DRAW);
        Assertions.assertThat(profit).isEqualTo(new Profit(0));
    }
}
