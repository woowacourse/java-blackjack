package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.CardDeck;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.Players;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GameResultTest {

    private Dealer dealer;
    private Players players;

    @BeforeEach
    void resetVariable() {
        dealer = new Dealer();
        players = new Players("Jamie");
    }

    @DisplayName("플레이어의 승패 - 패")
    @Test
    void checkPlayerResultLose() {
        dealer.drawCard(new CardDeck());
        GameResult gameResult = new GameResult(dealer, players);
        Map<Player, Outcome> playerResult = gameResult.getPlayersResult();

        for (Player player : playerResult.keySet()) {
            assertThat(playerResult.get(player)).isEqualTo(Outcome.PLAYER_LOSE);
        }
    }

    @DisplayName("플레이어의 승패 - 승")
    @Test
        // MethodSource
    void checkPlayerResultWin() {
        players.getPlayers().get(0).drawCard(new CardDeck());
        GameResult gameResult = new GameResult(dealer, players);
        Map<Player, Outcome> playerResult = gameResult.getPlayersResult();

        for (Player player : playerResult.keySet()) {
            assertThat(playerResult.get(player)).isEqualTo(Outcome.PLAYER_WIN);
        }
    }
}
