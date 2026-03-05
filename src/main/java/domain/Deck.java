package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private final List<Card> cards;

    private Deck(List<Card> cards) {
        this.cards = cards;
    }

    public static Deck createDeck() {
        List<Card> cards = new ArrayList<>();
        createAllCards(cards);
        Collections.shuffle(cards);
        return new Deck(cards);
    }

    private static void createAllCards(List<Card> cards) {
        for (CardShape shape : CardShape.values()) {
            for (CardContents content : CardContents.values()) {
                cards.add(new Card(shape, content));
            }
        }
    }
}
