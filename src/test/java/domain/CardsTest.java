package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CardsTest {

    @Test
    @DisplayName("카드 합계를 계산한다.")
    void calculateScore_ReturnSum() {
        Card card1 = new Card(Shape.SPADE, Number.EIGHT);
        Card card2 = new Card(Shape.HEART, Number.EIGHT);

        List<Card> cards = new ArrayList<>();
        Cards testCards = new Cards(cards);

        testCards.addCard(card1);
        testCards.addCard(card2);

        assertEquals(16, testCards.calculateScore());
    }

    @Test
    @DisplayName("Ace가 2개일 경우 추가로 카드를 받을 수 있다.")
    void canReceiveCard_TwoAces_ReturnTrue() {
        Card card1 = new Card(Shape.SPADE, Number.ACE);
        Card card2 = new Card(Shape.HEART, Number.ACE);

        List<Card> cards = new ArrayList<>();
        Cards realCards = new Cards(cards);

        realCards.addCard(card1);
        realCards.addCard(card2);

        assertEquals(true, realCards.canReceiveCard(21));
    }

    @Test
    @DisplayName("King,9,Ace,Ace일경우 추가로 카드를 받을 수 없다.")
    void canReceiveCard_KingNineTwoAces_ReturnFalse() {
        Card card1 = new Card(Shape.SPADE, Number.KING);
        Card card2 = new Card(Shape.HEART, Number.NINE);
        Card card3 = new Card(Shape.SPADE, Number.ACE);
        Card card4 = new Card(Shape.HEART, Number.ACE);

        List<Card> cards = new ArrayList<>();
        Cards realCards = new Cards(cards);

        realCards.addCard(card1);
        realCards.addCard(card2);
        realCards.addCard(card3);
        realCards.addCard(card4);

        assertEquals(false, realCards.canReceiveCard(21));
    }
}
