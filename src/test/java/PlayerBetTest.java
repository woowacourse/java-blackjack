import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import blackjack.domain.Card;
import blackjack.domain.Dealer;
import blackjack.domain.GameResult;
import blackjack.domain.Player;
import blackjack.domain.ProfitResults;
import blackjack.domain.Rank;
import blackjack.domain.ScoreCompareResult;
import blackjack.domain.Shape;
import blackjack.service.Game;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class PlayerBetTest {
    @Test
    void calculate_profit_when_player_win_except_blackjack() {
        Player player = new Player("player", 1000);

        assertThat(player.calculateProfit(ScoreCompareResult.PLAYER_WIN)).isEqualTo(1000);
    }

    @Test
    void calculate_profit_when_player_win_with_blackjack() {
        Player player = createPlayer("player", 1000,
                new Card(Rank.ACE, Shape.SPADE), new Card(Rank.TEN, Shape.HEART));

        assertThat(player.calculateProfit(ScoreCompareResult.PLAYER_WIN)).isEqualTo(1500);
    }

    @Test
    void calculate_profit_when_player_lose() {
        Player player = new Player("player", 1000);

        assertThat(player.calculateProfit(ScoreCompareResult.PLAYER_LOSE)).isEqualTo(-1000);
    }

    @Test
    void calculate_profit_when_player_draw() {
        Player player = new Player("player", 1000);

        assertThat(player.calculateProfit(ScoreCompareResult.PUSH)).isEqualTo(0);
    }

    @Test
    void calculate_total_profit_result() {
        Player winningPlayer = createPlayer("winningPlayer", 1000,
                new Card(Rank.QUEEN, Shape.SPADE), new Card(Rank.TEN, Shape.HEART));
        Player losingPlayer = createPlayer("losingPlayer", 1500,
                new Card(Rank.NINE, Shape.SPADE), new Card(Rank.EIGHT, Shape.HEART));
        Dealer dealer = createDealer(
                new Card(Rank.TEN, Shape.SPADE), new Card(Rank.EIGHT, Shape.HEART));

        Game game = new Game(List.of(winningPlayer, losingPlayer), dealer);
        GameResult gameResult = game.judgeTotalGameResult();

        ProfitResults actualProfitResults = game.calculateTotalProfitResults(gameResult);
        ProfitResults expectedProfitResults = new ProfitResults(500.0, Map.of(
                winningPlayer, 1000.0,
                losingPlayer, -1500.0
        ));
        assertThat(actualProfitResults).isEqualTo(expectedProfitResults);
    }


    private Player createPlayer(String name, int bettingAmount, Card... cards) {
        Player player = new Player(name, bettingAmount);
        for (Card card : cards) {
            player.receiveOneCard(card);
        }
        return player;
    }

    private Dealer createDealer(Card... cards) {
        Dealer dealer = new Dealer();
        for (Card card : cards) {
            dealer.receiveOneCard(card);
        }
        return dealer;
    }
}
