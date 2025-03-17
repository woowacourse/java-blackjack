package model;

import static model.casino.WinLossResult.DRAW;
import static model.casino.WinLossResult.LOSS;
import static model.casino.WinLossResult.WIN;
import static model.casino.WinLossResult.WIN_WITH_BLACK_JACK;
import static org.assertj.core.api.Assertions.assertThat;

import model.casino.Accountant;
import model.participants.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class AccountantTest {
    @Test
    @DisplayName("회계사는 플레이어들의 베팅 금액을 알고 있다.")
    void test1() {
        Accountant accountant = new Accountant();
        Player player = new Player("띠용");
        accountant.accountBettingPrice(player, 100000000);

        accountant.getBettingPrice(player);
    }

    @Nested
    @DisplayName("회계사는 결과에 따라 수익금을 계산한다.")
    class CalculateBettingPrice {
        @Test
        @DisplayName("블랙잭 승리 시 수익금은 베팅금의 0.5배 이다.")
        void test1() {
            // given
            Accountant accountant = new Accountant();
            Player player = new Player("띠용");
            accountant.accountBettingPrice(player, 10000);
            // when & then
            assertThat(accountant.getProfit(player, WIN_WITH_BLACK_JACK)).isEqualTo(5000);
        }

        @Test
        @DisplayName("일반 승리 시 수익금은 베팅금의 1배 이다.")
        void test2() {
            // given
            Accountant accountant = new Accountant();
            Player player = new Player("띠용");
            accountant.accountBettingPrice(player, 10000);
            // when & then
            assertThat(accountant.getProfit(player, WIN)).isEqualTo(10000);
        }

        @Test
        @DisplayName("무승부 시 수익금은 0 이다.")
        void test3() {
            // given
            Accountant accountant = new Accountant();
            Player player = new Player("띠용");
            accountant.accountBettingPrice(player, 10000);
            // when & then
            assertThat(accountant.getProfit(player, DRAW)).isEqualTo(0);
        }

        @Test
        @DisplayName("패배 시 수익금은 베팅금의 -1배 이다.")
        void test4() {
            // given
            Accountant accountant = new Accountant();
            Player player = new Player("띠용");
            accountant.accountBettingPrice(player, 10000);
            // when & then
            assertThat(accountant.getProfit(player, LOSS)).isEqualTo(-10000);
        }
    }

}