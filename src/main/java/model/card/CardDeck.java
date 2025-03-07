package model.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class CardDeck {

    private final List<Card> deck = initDeck();

    public CardDeck() {
    }

    private static List<Card> initDeck() {
        ArrayList<Card> deck = new ArrayList<>();
        for (SuitType suit : SuitType.values()) {
            for (RankType rank : RankType.values()) {
                deck.add(new Card(suit, rank));
            }
        }
        return deck;
    }

    public void shuffle() {
        Collections.shuffle(deck);
    }

    public List<Card> pickCard(int amount) {
        final List<Card> findCards = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            final Card card = deck.removeFirst();
            findCards.add(card);
        }
        return findCards;
    }

    public List<Card> getDeck() {
        return deck;
    }
}
