package domain;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameResultStatusTest {

    @DisplayName("BLACKJACK일 경우 베팅 금액의 1.5배 수익을 가진다")
    @Test
    void test1() {
        // given
        int bettingMoney = 1000;
        GameResultStatus gameResultStatus = GameResultStatus.BLACKJACK;
        // when
        BigDecimal profit = gameResultStatus.calculateProfit(bettingMoney);
        // then
        BigDecimal payout = BigDecimal.valueOf(1.5);
        Assertions.assertThat(profit).isEqualTo(payout.multiply(BigDecimal.valueOf(bettingMoney)));
    }

    @DisplayName("WIN일 경우 베팅 금액의 1배 수익을 가진다")
    @Test
    void test2() {
        // given
        int bettingMoney = 1000;
        GameResultStatus gameResultStatus = GameResultStatus.WIN;
        // when
        BigDecimal profit = gameResultStatus.calculateProfit(bettingMoney);
        // then
        BigDecimal payout = BigDecimal.valueOf(1);
        Assertions.assertThat(profit).isEqualTo(payout.multiply(BigDecimal.valueOf(bettingMoney)));
    }

    @DisplayName("DRAW일 경우 베팅 금액의 0배 수익을 가진다")
    @Test
    void test3() {
        // given
        int bettingMoney = 1000;
        GameResultStatus gameResultStatus = GameResultStatus.DRAW;
        // when
        BigDecimal profit = gameResultStatus.calculateProfit(bettingMoney);
        // then
        BigDecimal payout = BigDecimal.valueOf(0);
        Assertions.assertThat(profit).isEqualTo(payout.multiply(BigDecimal.valueOf(bettingMoney)));
    }

    @DisplayName("LOSE일 경우 베팅 금액의 -1배 수익을 가진다")
    @Test
    void test4() {
        // given
        int bettingMoney = 1000;
        GameResultStatus gameResultStatus = GameResultStatus.LOSE;
        // when
        BigDecimal profit = gameResultStatus.calculateProfit(bettingMoney);
        // then
        BigDecimal payout = BigDecimal.valueOf(-1);
        Assertions.assertThat(profit).isEqualTo(payout.multiply(BigDecimal.valueOf(bettingMoney)));
    }
}
