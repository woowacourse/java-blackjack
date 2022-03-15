package model.cardGettable;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import model.card.Card;
import model.card.CardFace;
import model.card.CardSuit;
import model.card.Cards;
import org.junit.jupiter.api.Test;

class FirstCardsGettableTest {

    @Test
    void getFirstCard() {
        FirstCardsGettable firstCardsGettable = new FirstCardsGettable();
        List<Card> cards = firstCardsGettable.getCards(new Cards(List.of(new Card(CardSuit.SPADE, CardFace.ACE))));
        assertThat(cards).contains(new Card(CardSuit.SPADE, CardFace.ACE));
    }
}