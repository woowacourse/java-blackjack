package util;

import domain.card.Card;
import domain.card.Deck;
import domain.random.RandomValueGenerator;
import java.util.List;

public class DeckCreator {

    public static Deck createDeck(List<Card> cards, RandomValueGenerator randomValueGenerator) {
        return new Deck(cards, randomValueGenerator);
    }
}
