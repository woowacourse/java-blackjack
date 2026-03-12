import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import blackjack.domain.Card;
import blackjack.domain.Dealer;
import blackjack.domain.GameResult;
import blackjack.domain.Participant;
import blackjack.domain.Player;
import blackjack.domain.ProfitResults;
import blackjack.domain.ScoreCompareResult;
import blackjack.service.Game;
import java.util.HashMap;
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
        Player player = createPlayer("player", 1000, "A:스페이드", "10:하트");

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
        Player winningPlayer = createPlayer("winningPlayer", 1000, "Q:스페이드", "10:하트");
        Player losingPlayer = createPlayer("losingPlayer", 1500, "9:스페이드", "8:하트");
        Dealer dealer = createDealer("10:스페이드", "8:하트");

        Game game = new Game(List.of(winningPlayer, losingPlayer), dealer);
        GameResult gameResult = game.judgeTotalGameResult();

        ProfitResults actualProfitResults = game.calculateTotalProfitResults(gameResult);
        Map<Participant, Integer> expectedProfitMap = new HashMap<>() {{
            put(dealer, 500);
            put(winningPlayer, 1000);
            put(losingPlayer, -1500);
        }};
        ProfitResults expectedProfitResults = new ProfitResults(expectedProfitMap);

        assertThat(actualProfitResults).isEqualTo(expectedProfitResults);
    }


    private Player createPlayer(String name, int bettingAmount, String... cards) {
        Player player = new Player(name, bettingAmount);
        for (String card : cards) {
            String[] parts = card.split(":");
            player.receiveOneCard(new Card(parts[0], parts[1]));
        }
        return player;
    }

    private Dealer createDealer(String... cards) {
        Dealer dealer = new Dealer();
        for (String card : cards) {
            String[] parts = card.split(":");
            dealer.receiveOneCard(new Card(parts[0], parts[1]));
        }
        return dealer;
    }
}
