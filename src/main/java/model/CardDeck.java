package model;

import java.util.ArrayList;
import java.util.Collections;
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
        Collections.shuffle(deck);
        return deck;
    }

    private CardDeck() {
    }

    public static List<Card> getCards() {
        return cards;
    }

    public static List<Card> pickCard(int amount) {
        final List<Card> findCards = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            final Card card = cards.removeFirst();
            findCards.add(card);
        }
        return findCards;
    }
}
