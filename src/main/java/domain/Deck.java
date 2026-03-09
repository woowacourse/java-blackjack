package domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import vo.Rank;
import vo.Suit;

public class Deck {
    private final List<Card> cards;

    public Deck() {
        this.cards = makeCards();
        Collections.shuffle(cards);
    }

    public Deck(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public Card dealCard() {
        return cards.removeFirst();
    }

    private List<Card> makeCards() {
        List<Card> cards = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            cards.addAll(createCardsBySuit(suit));
        }
        return cards;
    }

    private List<Card> createCardsBySuit(Suit suit) {
        List<Card> cards = new ArrayList<>();
        for (Rank rank : Rank.values()) {
            cards.add(new Card(suit, rank));
        }
        return cards;
    }
}
