package domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardsTest {

    @Test
    @DisplayName("카드를 52장 생성한다.")
    void createCards_Returns52() {
        Cards standardCards = Cards.createCards();
        int actualValue = Cards.getCardsSize(standardCards);
        assertEquals(52, actualValue);
    }

    @Test
    @DisplayName("이미 생성된 카드를 섞는다.")
    void shuffleCards_ReturnShuffledCards() {
        Cards standardCards = Cards.createCards();
        List<Card> beforeShuffle = new ArrayList<>(standardCards.getCards());
        Cards.shuffleCards(standardCards);
        List<Card> afterShuffle = standardCards.getCards();
        assertNotEquals(beforeShuffle, afterShuffle);
    }

    @Test
    @DisplayName("카드 합계를 계산한다.")
    void calculateScore_ContainsNonAce_ReturnSum() {
        Card card1 = new Card(Shape.SPADE, Number.EIGHT);
        Card card2 = new Card(Shape.HEART, Number.EIGHT);

        List<Card> cards = new ArrayList<>();
        Cards testCards = new Cards(cards);

        testCards.addCard(card1);
        testCards.addCard(card2);

        assertEquals(16, testCards.calculateScore());
    }

    @Test
    @DisplayName("ACE가 포함되었을 때 카드 합계를 계산한다.")
    void calculateScore_ContainsAce_ReturnSum() {
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
