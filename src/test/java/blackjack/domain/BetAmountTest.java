package blackjack.domain;

import blackjack.domain.game.GameResult;
import blackjack.domain.player.BetAmount;
import blackjack.domain.player.Profit;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BetAmountTest {
    @Test
    @DisplayName("겜블러가 패배했다면 배팅금액을 잃는다")
    void testBetAmount() {
        BetAmount betAmount = new BetAmount(10000);
        Profit profit = Profit.calculateFrom(betAmount.amount(), GameResult.LOSE);
        assertThat(profit.value()).isEqualTo(-10000);
    }

    @Test
    @DisplayName("겜블러가 블랙잭이라면 배팅 금액의 1.5배를 받는다")
    void testBetAmount1() {
        BetAmount betAmount = new BetAmount(10000);
        Profit profit = Profit.calculateFrom(betAmount.amount(), GameResult.BLACKJACK);
        assertThat(profit.value()).isEqualTo((int) (10000 * 1.5));
    }

    @Test
    @DisplayName("겜블러가 승리하면 배팅 금액을 얻는다")
    void testBetAmount2() {
        BetAmount betAmount = new BetAmount(10000);
        Profit profit = Profit.calculateFrom(betAmount.amount(), GameResult.WIN);
        assertThat(profit.value()).isEqualTo(10000);
    }

    @Test
    @DisplayName("비기면 수익이 없다")
    void testBetAmount3() {
        BetAmount betAmount = new BetAmount(10000);
        Profit profit = Profit.calculateFrom(betAmount.amount(), GameResult.DRAW);
        assertThat(profit.value()).isEqualTo(0);
    }

    @Test
    @DisplayName("배팅 금액이 음수면 예외를 던진다")
    void testBetAmount4() {
        Assertions.assertThatThrownBy(
                () -> new BetAmount(-1000)
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
