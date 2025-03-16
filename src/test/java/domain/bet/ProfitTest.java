package domain.bet;

import domain.result.WinLossResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProfitTest {
    @Test
    @DisplayName("플레이어가 블랙잭이면 베팅 금액의 1.5배를 받는다.")
    void test3() {
        BetMoney betMoney = new BetMoney(10000);

        Profit profit = new Profit(WinLossResult.BLACKJACK_WIN, betMoney.getAmount());

        assertThat(profit.getProfit()).isEqualTo(5000);
    }

    @Test
    @DisplayName("딜러가 버스트 시 살아남은 플레이어라면 베팅 금액의 2배를 받는다.")
    void test4() {
        BetMoney betMoney = new BetMoney(10000);

        Profit profit = new Profit(WinLossResult.WIN, betMoney.getAmount());

        assertThat(profit.getProfit()).isEqualTo(10000);
    }

    @Test
    @DisplayName("플레이어가 버스트 시 베팅 금액을 모두 잃는다.")
    void test5() {
        BetMoney betMoney = new BetMoney(10000);

        Profit profit = new Profit(WinLossResult.LOSS, betMoney.getAmount());

        assertThat(profit.getProfit()).isEqualTo(-10000);
    }

    @Test
    @DisplayName("무승부 시에는 금액을 그대로 돌려받는다.")
    void test6() {
        BetMoney betMoney = new BetMoney(10000);

        Profit profit = new Profit(WinLossResult.DRAW, betMoney.getAmount());

        assertThat(profit.getProfit()).isEqualTo(0);
    }
}