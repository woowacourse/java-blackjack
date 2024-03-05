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
}
