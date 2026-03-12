import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import blackjack.domain.Card;
import blackjack.domain.Player;
import blackjack.domain.PlayerBetting;
import blackjack.domain.ScoreCompareResult;
import org.junit.jupiter.api.Test;

public class PlayerBetTest {
    @Test
    void calculate_profit_when_player_win_except_blackjack() {
        Player player = new Player("player");

        PlayerBetting playerBetting = new PlayerBetting(player, 1000);

        assertThat(playerBetting.calculateProfit(ScoreCompareResult.PLAYER_WIN)).isEqualTo(1000);
    }

    @Test
    void calculate_profit_when_player_win_with_blackjack() {
        Player player = createPlayer("player", "A:스페이드", "10:하트");

        PlayerBetting playerBetting = new PlayerBetting(player, 1000);

        assertThat(playerBetting.calculateProfit(ScoreCompareResult.PLAYER_WIN)).isEqualTo(1500);
    }

    @Test
    void calculate_profit_when_player_lose() {
        Player player = new Player("player");

        PlayerBetting playerBetting = new PlayerBetting(player, 1000);

        assertThat(playerBetting.calculateProfit(ScoreCompareResult.PLAYER_LOSE)).isEqualTo(-1000);
    }

    @Test
    void calculate_profit_when_player_draw() {
        Player player = new Player("player");

        PlayerBetting playerBetting = new PlayerBetting(player, 1000);

        assertThat(playerBetting.calculateProfit(ScoreCompareResult.PUSH)).isEqualTo(0);
    }


    private Player createPlayer(String name, String... cards) {
        Player player = new Player(name);
        for (String card : cards) {
            String[] parts = card.split(":");
            player.receiveOneCard(new Card(parts[0], parts[1]));
        }
        return player;
    }
}
