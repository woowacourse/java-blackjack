package model;

import java.util.ArrayList;
import java.util.List;

public final class CardDeck {

    private static final List<Card> cards;

    static {
        cards = initCards();
    }

    private static List<Card> initCards() {
        ArrayList<Card> deck = new ArrayList<>();
        for (SuitType suit : SuitType.values()) {
            for (RankType rank : RankType.values()) {
                deck.add(new Card(suit, rank));
            }
        }
        return deck;
    }

    private CardDeck() {
    }

    public static List<Card> getCards() {
        return cards;
    }
}
