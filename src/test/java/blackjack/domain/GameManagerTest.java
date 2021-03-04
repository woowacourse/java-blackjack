package blackjack.domain;

import blackjack.domain.game.GameManager;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Players;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameManagerTest {
    @Test
    @DisplayName("모든 플레이어에게 카드 주는지 확인")
    void giveAllPlayersCard() {
        Players players = new Players("pobi,jason", new Dealer());
        GameManager gameManager = new GameManager(players);
        gameManager.giveCards();
        assertEquals(players.getPlayers().size(), 2);
        assertTrue(players.getPlayers().stream().allMatch(player -> player.toList().size() == 2));
    }
}
