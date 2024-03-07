package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @Test
    @DisplayName("플레이어는 카드 덱이 Bust되지 않았으면 hit할 수 있다.")
    void canHit_ShouldReturnTrue_WhenCardDeckIsNotBust() {
        CardDeck deck = new CardDeck();

        deck.addCard(Card.from(Number.TEN, Emblem.SPADE));
        deck.addCard(Card.from(Number.NINE, Emblem.CLUB));
        deck.addCard(Card.from(Number.TWO, Emblem.HEART));

        Participant participant = new Player(new Name("name"), deck);

        assertTrue(participant.canHit());
    }

    @Test
    @DisplayName("플레이어는 카드 덱이 Bust되면 hit할 수 없다.")
    void canHit_ShouldReturnFalse_WhenCardDeckIsBust() {
        CardDeck deck = new CardDeck();
        deck.addCard(Card.from(Number.TEN, Emblem.SPADE));
        deck.addCard(Card.from(Number.TEN, Emblem.CLUB));
        deck.addCard(Card.from(Number.TWO, Emblem.HEART));
        Participant participant = new Player(new Name("name"), deck);

        assertFalse(participant.canHit());
    }

}
