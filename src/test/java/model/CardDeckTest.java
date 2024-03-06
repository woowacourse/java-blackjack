package model;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardDeckTest {

    @Test
    @DisplayName("CardDeck에 존재하는 Card 목록에 Card를 추가한다.")
    void addCard_InsertAnotherCardInCardList_UpdatedCardList() {

        CardDeck cardDeck = new CardDeck();

        Card expectedCard = Card.from(Number.THREE, Emblem.CLUB);
        cardDeck.addCard(expectedCard);

        List<Card> cards = cardDeck.getCards();
        Card actualCard = cards.get(0);

        assertEquals(expectedCard, actualCard);
    }

    @Test
    @DisplayName("CardDeck이 가지고 있는 카드들의 숫자 합인 Hand를 반환한다.")
    void calculateHand_ShouldReturnCorrectValue_WhenDeckContainsSpecificCards() {

        CardDeck cardDeck = new CardDeck();
        cardDeck.addCard(Card.from(Number.THREE, Emblem.CLUB));
        cardDeck.addCard(Card.from(Number.FIVE, Emblem.DIAMOND));
        cardDeck.addCard(Card.from(Number.SEVEN, Emblem.HEART));

        int expectedSum = 15;

        int actualSum = cardDeck.calculateHand();
        assertEquals(expectedSum, actualSum);
    }

    @Test
    @DisplayName("CardDeck이 비어있을 때, Hand를 구하면 0을 반환한다.")
    void calculateHand_ShouldReturnCorrectValue_WhenDeckIsEmpty() {

        CardDeck cardDeck = new CardDeck();

        int expectedSum = 0;

        int actualSum = cardDeck.calculateHand();

        assertEquals(expectedSum, actualSum);
    }

    @Test
    @DisplayName("카드 덱의 hand가 21을 초과하였을 때 true를 반환한다.")
    void isBust_ShouldReturnTrue_WhenHandIsOver21() {
        CardDeck cardDeck = new CardDeck();
        cardDeck.addCard(Card.from(Number.SEVEN, Emblem.CLUB));
        cardDeck.addCard(Card.from(Number.SEVEN, Emblem.DIAMOND));
        cardDeck.addCard(Card.from(Number.EIGHT, Emblem.HEART));

        assertTrue(cardDeck.isBust());
    }


    @Test
    @DisplayName("카드 덱의 hand가 21이하였을 때 false를 반환한다.")
    void isBust_ShouldReturnFalse_WhenHandIsLessThen22() {
        CardDeck cardDeck = new CardDeck();
        cardDeck.addCard(Card.from(Number.SEVEN, Emblem.CLUB));
        cardDeck.addCard(Card.from(Number.SEVEN, Emblem.DIAMOND));
        cardDeck.addCard(Card.from(Number.SEVEN, Emblem.HEART));

        assertFalse(cardDeck.isBust());
    }

    @Test
    @DisplayName("카드를 히트하여 덱에 더할 때 버스트되면, 소프트 에이스를 한 장 찾아 하드 에이스로 변경하여 계산한다.")
    void calculateHand_ShouldNotReturnBustHand_WhenHavingSoftAce() {
        // Given
        CardDeck cardDeck = new CardDeck();
        cardDeck.addCard(Card.from(Number.ACE, Emblem.SPADE));
        cardDeck.addCard(Card.from(Number.NINE, Emblem.SPADE));

        // When
        cardDeck.addCard(Card.from(Number.TEN, Emblem.SPADE));

        // Then
        assertAll(() -> {
            assertNotEquals(30, cardDeck.calculateHand());
            assertEquals(20, cardDeck.calculateHand());
        });
    }

    @Test
    @DisplayName("카드를 히트하여 덱에 더할 때 버스트되면, 소프트 에이스를 한 장 찾아 하드 에이스로 변경하여 계산한다.")
    void calculateHand_ShouldReturnBustHand_WhenNotHavingSoftAce() {
        CardDeck deck = new CardDeck();
        deck.addCard(Card.from(Number.QUEEN, Emblem.SPADE));
        deck.addCard(Card.from(Number.KING, Emblem.CLUB));
        deck.addCard(Card.from(Number.JACK, Emblem.DIAMOND));

        assertEquals(30, deck.calculateHand());
    }
}
