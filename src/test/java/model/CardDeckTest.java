package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardDeckTest {

    @Test
    @DisplayName("CardDeck에 존재하는 Card 목록에 Card를 추가한다.")
    void addCard_InsertAnotherCardInCardList_UpdatedCardList() {

        CardDeck cardDeck = new CardDeck();

        Card expectedCard = new Card(Number.THREE, Emblem.CLUB);
        cardDeck.addCard(expectedCard);

        List<Card> cards = cardDeck.getCards();
        Card actualCard = cards.get(0);

        assertEquals(expectedCard, actualCard);
    }

    @Test
    @DisplayName("CardDeck이 가지고 있는 카드들의 숫자 합인 Hand를 반환한다.")
    void calculateHand_ShouldReturnCorrectValue_WhenDeckContainsSpecificCards(){

        CardDeck cardDeck = new CardDeck();
        cardDeck.addCard(new Card(Number.THREE, Emblem.CLUB));
        cardDeck.addCard(new Card(Number.FIVE, Emblem.DIAMOND));
        cardDeck.addCard(new Card(Number.SEVEN, Emblem.HEART));

        int expectedSum = 15;

        int actualSum = cardDeck.calculateHand();
        assertEquals(expectedSum, actualSum);
    }

    @Test
    @DisplayName("CardDeck이 비어있을 때, Hand를 구하면 0을 반환한다.")
    void calculateHand_ShouldReturnCorrectValue_WhenDeckIsEmpty(){

        CardDeck cardDeck = new CardDeck();

        int expectedSum = 0;

        int actualSum = cardDeck.calculateHand();

        assertEquals(expectedSum, actualSum);
    }


}
