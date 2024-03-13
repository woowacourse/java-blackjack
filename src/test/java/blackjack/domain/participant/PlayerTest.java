package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.deck.Card;
import blackjack.domain.deck.Deck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @DisplayName("플레이어가 카드 추가를 승낙하면, 카드패에 한장이 추가된다")
    @Test
    void should_AddCard_When_PlayerAcceptDraw() {
        Deck deck = Deck.createShuffledDeck();
        Player testPlayer = new Player("pobi", new BetMoney(1));
        testPlayer.addCard(Card.valueOf(0));
        testPlayer.addCard(Card.valueOf(1));

        testPlayer.attemptDraw(() -> Boolean.TRUE, deck);

        assertThat(testPlayer.getHandsCards()).hasSize(3);
    }

    @DisplayName("플레이어가 카드 추가를 거부하면, 카드는 추가되지 않는다")
    @Test
    void should_NotAddCard_When_PlayerRejectDraw() {
        Deck deck = Deck.createShuffledDeck();
        Player testPlayer = new Player("pobi", new BetMoney(1));
        testPlayer.addCard(Card.valueOf(0));
        testPlayer.addCard(Card.valueOf(1));

        testPlayer.attemptDraw(() -> Boolean.FALSE, deck);

        assertThat(testPlayer.getHandsCards()).hasSize(2);
    }
}
