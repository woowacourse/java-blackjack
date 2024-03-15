package domain;

import domain.card.Card;
import domain.card.Deck;
import java.util.List;

public class TestDeck extends Deck {

    private TestDeck(List<Card> cards) {
        super(cards);
    }

    public static Deck withCustomCards(Card... cards) {
        return Deck.withCustomCards(cards);
    }
}
