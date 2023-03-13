package domain.card;

import domain.strategy.IndexGenerator;

import java.util.ArrayList;
import java.util.List;

public class Deck {
    private final List<Card> cards;
    private final IndexGenerator indexGenerator;

    private Deck(List<Card> cards, IndexGenerator indexGenerator) {
        this.cards = cards;
        this.indexGenerator = indexGenerator;
    }

    public static Deck create(IndexGenerator indexGenerator) {
        return new Deck(initializeCards(), indexGenerator);
    }

    private static List<Card> initializeCards() {
        List<Card> cards = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            initializeCardsByShape(cards, suit);
        }

        return cards;
    }

    private static void initializeCardsByShape(List<Card> cards, Suit suit) {
        for (Denomination denomination : Denomination.values()) {
            cards.add(new Card(suit, denomination));
        }
    }

    public Card findAnyOneCard() {
        int index = this.indexGenerator.generate(cards.size());
        return cards.remove(index);
    }
}
