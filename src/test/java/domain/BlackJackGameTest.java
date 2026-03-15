package domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlackJackGameTest {
    @Test
    @DisplayName("게임 시작 시 딜러와 모든 플레이어는 카드 2장을 가진다")
    void startGameWithInitialCards() {
        BlackJackGame game = BlackJackGame.startGame("pobi,jason");

        assertEquals(2, game.dealer().getCardList().size());
        game.players().forEachPlayer(player -> assertEquals(2, player.getCardList().size()));
    }
}
