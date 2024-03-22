package blackjack.domain.betting;

import blackjack.domain.result.GameResult;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class BettingMoneyTest {

    @DisplayName("게임 결과가 블랙잭이면 배팅 금액의 1.5배를 수익으로 받는다.")
    @Test
    void blackjackCalculateTest() {
        // given
        BettingMoney bettingMoney = new BettingMoney(10000);

        // when
        BigDecimal profit = bettingMoney.calculateProfit(GameResult.BLACKJACK);

        // then
        Assertions.assertThat(profit).isEqualTo(BigDecimal.valueOf(15000.0));
    }

    @DisplayName("게임 결과가 블랙잭이면 배팅 금액의 0배를 수익으로 받는다.")
    @Test
    void drawCalculateTest() {
        // given
        BettingMoney bettingMoney = new BettingMoney(10000);

        // when
        BigDecimal profit = bettingMoney.calculateProfit(GameResult.DRAW);

        // then
        Assertions.assertThat(profit).isEqualTo(BigDecimal.valueOf(0));
    }

    @DisplayName("게임 결과가 블랙잭이면 배팅 금액의 1배를 수익으로 받는다.")
    @Test
    void winTest() {
        // given
        BettingMoney bettingMoney = new BettingMoney(10000);

        // when
        BigDecimal profit = bettingMoney.calculateProfit(GameResult.WIN);


        // then
        Assertions.assertThat(profit).isEqualTo(BigDecimal.valueOf(10000));
    }

    @DisplayName("게임 결과가 블랙잭이면 배팅 금액의 -1배를 수익으로 받는다.")
    @Test
    void loseTest() {
        // given
        BettingMoney bettingMoney = new BettingMoney(10000);

        // when
        BigDecimal profit = bettingMoney.calculateProfit(GameResult.LOSE);

        // then
        Assertions.assertThat(profit).isEqualTo(BigDecimal.valueOf(-10000));
    }
}
