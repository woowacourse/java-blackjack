package service;

import domain.card.Card;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class GameManagerTest {

    @Test
    @DisplayName("딜러와 모든 플레이어는 게임 시작 시, 2장의 카드를 받는다.")
    void 게임_시작시_2장_카드() {
        // given
        List<String> players = List.of("pobi", "james");

        GameManager gameManager = new GameManager();

        // when
        gameManager.addPlayers(players);
        gameManager.dealInitialCardsToParticipants();

        // then
        Assertions.assertEquals(gameManager.toDealerHandDto().getDealerHand().size(), 2);
        for (List<Card> cards : gameManager.toPlayersHandDto().getPlayersHand().values()) {
            Assertions.assertEquals(cards.size(), 2);
        }
    }
}
