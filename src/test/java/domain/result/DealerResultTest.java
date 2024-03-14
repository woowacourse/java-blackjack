package domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import domain.Profit;
import domain.WinState;
import domain.cards.Hand;
import domain.gamer.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerResultTest {

    @DisplayName("플레이어의 수익을 바탕으로 딜러의 수익을 계산한다.")
    @Test
    void calculateProfit() {
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
        playersResult.calculateProfit(player3, WinState.DRAW);
        playersResult.calculateProfit(player4, WinState.BLACKJACK);

        DealerResult dealerResult = new DealerResult();
        dealerResult.calculateProfit(playersResult);

        assertThat(dealerResult.getProfit().getValue()).isEqualTo(-15);
    }
}