package domain;

import domain.gamer.Dealer;
import domain.gamer.Player;
import domain.judge.WinState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class CashierTest {

    Player player;
    Dealer dealer;
    BetAmount betAmount;
    Cashier cashier;

    @BeforeEach
    void setup() {
        player = new Player("player");
        dealer = new Dealer();
        betAmount = new BetAmount(10000);
        cashier = new Cashier(List.of(new PlayerBet(player, betAmount)));
    }

    @DisplayName("승리하면 베팅액의 1배를 준다.")
    @Test
    void testCashierCalculateWinProfit() {
        Map<Player, BetAmount> result = cashier.calculatePlayersProfits(Map.of(player, WinState.WIN), dealer);
        assertThat(result.get(player).getBetAmount())
                .isEqualTo(10000);
    }

    @DisplayName("패배하면 베팅액의 -1배를 준다.")
    @Test
    void testCashierCalculateLoseProfit() {
        Map<Player, BetAmount> result = cashier.calculatePlayersProfits(Map.of(player, WinState.LOSE), dealer);
        assertThat(result.get(player).getBetAmount())
                .isEqualTo(-10000);
    }

    @DisplayName("무승부하면 0을 준다.")
    @Test
    void testCashierCalculateDrawProfit() {
        Map<Player, BetAmount> result = cashier.calculatePlayersProfits(Map.of(player, WinState.DRAW), dealer);
        assertThat(result.get(player).getBetAmount())
                .isEqualTo(0);
    }

    @DisplayName("블랙잭이면 베팅액의 1.5배를 준다.")
    @Test
    void testCashierCalculateBlackJackProfit() {
        Map<Player, BetAmount> result = cashier.calculatePlayersProfits(Map.of(player, WinState.BLACK_JACK), dealer);
        assertThat(result.get(player).getBetAmount())
                .isEqualTo(15000);
    }

    @DisplayName("딜러의 수익을 계산한다.")
    @Test
    void testCashierCalculateDealerProfit() {
        Player player1 = new Player("player1");
        BetAmount betAmount1 = new BetAmount(10000);
        Player player2 = new Player("player2");
        BetAmount betAmount2 = new BetAmount(20000);

        Cashier cashier = new Cashier(List.of(new PlayerBet(player1, betAmount1), new PlayerBet(player2, betAmount2)));

        Map<Player, BetAmount> result1 = cashier.calculatePlayersProfits(Map.of(player1, WinState.LOSE, player2, WinState.BLACK_JACK), dealer);
        assertThat(result1.get(dealer).getBetAmount()).isEqualTo(-20000);
    }
}
