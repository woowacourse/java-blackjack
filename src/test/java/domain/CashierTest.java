package domain;

import domain.gamer.Player;
import domain.judge.WinState;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CashierTest {

    Player player;
    BetAmount betAmount;
    Cashier cashier;

    @BeforeEach
    void setup() {
        player = new Player("player");
        betAmount = new BetAmount("10000");
        cashier = new Cashier(List.of(new PlayerBet(player, betAmount)));
    }

    @DisplayName("승리하면 베팅액의 1배를 알려준다.")
    @Test
    void testCahsierCalculateWinProfit() {
        Assertions.assertThat(cashier.calculateProfit(player, WinState.WIN)).isEqualTo(10000);
    }

    @DisplayName("패배하면 베팅액의 -1배를 알려준다.")
    @Test
    void testCahsierCalculateLoseProfit() {
        Assertions.assertThat(cashier.calculateProfit(player, WinState.LOSE)).isEqualTo(-10000);
    }

    @DisplayName("무승부하면 0을 알려준다.")
    @Test
    void testCahsierCalculateDrawProfit() {
        Assertions.assertThat(cashier.calculateProfit(player, WinState.DRAW)).isEqualTo(0);
    }

    @DisplayName("블랙잭이면 베팅액의 1.5배를 알려준다.")
    @Test
    void testCahsierCalculateBlackJackProfit() {
        Assertions.assertThat(cashier.calculateProfit(player, WinState.BLACK_JACK)).isEqualTo(15000);
    }
}
