package blackjack.domain.participant;

import blackjack.domain.card.Deck;
import blackjack.domain.game.GameManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameManagerTest {
    @Test
    @DisplayName("모든 플레이어에게 카드 주는지 확인")
    void giveAllPlayersCard() {
        List<String> input = Arrays.asList("pobi", "jason");
        Players players = new Players(input, new Dealer());
        GameManager gameManager = new GameManager(players);
        gameManager.giveCards(new Deck());
        for (Player player : players.getPlayers()) {
            assertEquals(player.toList().size(), 2);
        }
        assertEquals(players.getPlayers().size(), 2);
        assertTrue(players.getPlayers().stream().allMatch(player -> player.toList().size() == 2));
    }
}
