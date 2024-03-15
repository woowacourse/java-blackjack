package blackjack.domain.participant;

import blackjack.domain.card.Card;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static blackjack.domain.card.CardKind.SPADE;
import static blackjack.domain.card.CardNumber.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PlayerTest {

    @DisplayName("플레이어가 카드를 추가하면, 카드패에 한장이 추가된다")
    @Test
    void should_AddCard() {
        Player testPlayer = new Player("pobi");
        testPlayer.addCard(new Card(SPADE, ACE));
        assertThat(testPlayer.getHandsCards()).hasSize(1);
    }

    @DisplayName("플레이어가 버스트가 아니라면, 한장을 더 받을 수 있다")
    @Test
    
    void should_getFinishedState_When_PlayerBust() {
        Player testPlayer = new Player("pobi");
        testPlayer.addCard(new Card(SPADE, JACK));
        testPlayer.addCard(new Card(SPADE, QUEEN));

        assertTrue(testPlayer::canAddCard);
    }

    @DisplayName("플레이어가 버스트 상태이면 한장을 더 받을 수 없다")
    @Test
    void should_getFinishedState_When_PlayerReject_Draw() {
        Player testPlayer = new Player("pobi");

        testPlayer.addCard(new Card(SPADE, JACK));
        testPlayer.addCard(new Card(SPADE, QUEEN));
        testPlayer.addCard(new Card(SPADE, KING));

        assertFalse(testPlayer::canAddCard);
    }
}
