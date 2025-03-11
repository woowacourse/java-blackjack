package model.card;

import java.util.*;

public final class CardDeck {

    private final List<Card> deck = initDeck();

    private List<Card> initDeck() {
        ArrayList<Card> deck = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (NormalRank rank : NormalRank.values()) {
                deck.add(new Card(suit, rank));
            }
            deck.add(new Card(suit, AceRank.SOFT_ACE));
        }
        return deck;
    }

    public void shuffle() {
        Collections.shuffle(deck);
    }

    public Card pickCard() {
        return deck.removeFirst();
    }

    public List<Card> getDeck() {
        return deck;
    }
}
