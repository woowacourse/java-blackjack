package model.cardGettable;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import model.card.Card;
import model.card.Cards;
import org.junit.jupiter.api.Test;

class FirstCardsGettableTest {

    @Test
    void getEmptyCard() {
        FirstCardsGettable firstCardsGettable = new FirstCardsGettable();
        List<Card> cards = firstCardsGettable.getCards(new Cards(List.of()));
        assertTrue(cards.isEmpty());
    }
}