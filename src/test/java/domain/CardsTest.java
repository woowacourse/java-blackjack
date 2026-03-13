package domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class CardsTest {

    @Test
    void 에이스가_없으면_카드_숫자의_합을_반환한다() {
        Card card1 = new Card(Shape.SPADE, Number.EIGHT);
        Card card2 = new Card(Shape.HEART, Number.EIGHT);

        List<Card> cards = new ArrayList<>();
        Cards testCards = new Cards(cards);

        testCards.addCard(card1);
        testCards.addCard(card2);

        assertEquals(16, testCards.calculateScore());
    }

    @Test
    void 에이스가_있으면_상황에_맞는_최대_합을_반환한다() {
        Card card1 = new Card(Shape.SPADE, Number.ACE);
        Card card2 = new Card(Shape.HEART, Number.FIVE);
        Card card3 = new Card(Shape.CLUB, Number.EIGHT);

        List<Card> cards = new ArrayList<>();
        Cards testCards = new Cards(cards);

        testCards.addCard(card1);
        testCards.addCard(card2);
        testCards.addCard(card3);

        assertEquals(14, testCards.calculateScore());
    }
}
