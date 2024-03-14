package domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import domain.Profit;
import domain.WinState;
import domain.cards.Hand;
import domain.gamer.Player;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersResultTest {

    @DisplayName("플레이어들의 승패 상태를 바탕으로 수익을 계산한다.")
    @Test
    void calculateProfitOfPlayers() {
        Player player1 = new Player("p1", new Hand());
        Player player2 = new Player("p2", new Hand());
        Player player3 = new Player("p3", new Hand());
        Player player4 = new Player("p4", new Hand());
        PlayersResult playersResult = new PlayersResult();
        playersResult.addProfit(player1, new Profit(10));
        playersResult.addProfit(player2, new Profit(10));
        playersResult.addProfit(player3, new Profit(10));
        playersResult.addProfit(player4, new Profit(10));

        playersResult.calculateProfit(player1, WinState.WIN);
        playersResult.calculateProfit(player2, WinState.LOSE);
        playersResult.calculateProfit(player3, WinState.BLACKJACK);
        playersResult.calculateProfit(player4, WinState.DRAW);

        Map<Player, Profit> profitResult = playersResult.getResult();
        assertThat(profitResult.get(player1).getValue()).isEqualTo(10);
        assertThat(profitResult.get(player2).getValue()).isEqualTo(-10);
        assertThat(profitResult.get(player3).getValue()).isEqualTo(15);
        assertThat(profitResult.get(player4).getValue()).isEqualTo(0);
    }
}