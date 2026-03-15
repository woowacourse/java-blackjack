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
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class PlayerBetTest {
    @Test
    void calculate_profit_when_player_win_except_blackjack() {
        Player player = new Player("player", BigDecimal.valueOf(1000));

        assertThat(player.calculateProfit(ScoreCompareResult.PLAYER_WIN)).isEqualByComparingTo(BigDecimal.valueOf(1000));
    }

    @Test
    void calculate_profit_when_player_win_with_blackjack() {
        Player player = createPlayer("player", BigDecimal.valueOf(1000),
                new Card(Rank.ACE, Shape.SPADE), new Card(Rank.TEN, Shape.HEART));

        assertThat(player.calculateProfit(ScoreCompareResult.PLAYER_WIN)).isEqualByComparingTo(BigDecimal.valueOf(1500));
    }

    @Test
    void calculate_profit_when_player_lose() {
        Player player = new Player("player", BigDecimal.valueOf(1000));

        assertThat(player.calculateProfit(ScoreCompareResult.PLAYER_LOSE)).isEqualByComparingTo(BigDecimal.valueOf(-1000));
    }

    @Test
    void calculate_profit_when_player_draw() {
        Player player = new Player("player", BigDecimal.valueOf(1000));

        assertThat(player.calculateProfit(ScoreCompareResult.PUSH)).isEqualByComparingTo(BigDecimal.ZERO);
    }

    @Test
    void calculate_total_profit_result() {
        Player winningPlayer = createPlayer("winningPlayer", BigDecimal.valueOf(1000),
                new Card(Rank.QUEEN, Shape.SPADE), new Card(Rank.TEN, Shape.HEART));
        Player losingPlayer = createPlayer("losingPlayer", BigDecimal.valueOf(1500),
                new Card(Rank.NINE, Shape.SPADE), new Card(Rank.EIGHT, Shape.HEART));
        Dealer dealer = createDealer(
                new Card(Rank.TEN, Shape.SPADE), new Card(Rank.EIGHT, Shape.HEART));

        Game game = new Game(List.of(winningPlayer, losingPlayer), dealer);
        GameResult gameResult = game.judgeTotalGameResult();

        ProfitResults actualProfitResults = game.calculateTotalProfitResults(gameResult);
        ProfitResults expectedProfitResults = new ProfitResults(BigDecimal.valueOf(500), Map.of(
                winningPlayer, BigDecimal.valueOf(1000),
                losingPlayer, BigDecimal.valueOf(-1500)
        ));
        assertThat(actualProfitResults).usingRecursiveComparison()
                .withComparatorForType(BigDecimal::compareTo, BigDecimal.class)
                .isEqualTo(expectedProfitResults);
    }


    private Player createPlayer(String name, BigDecimal bettingAmount, Card... cards) {
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