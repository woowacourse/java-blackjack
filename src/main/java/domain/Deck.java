package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import vo.Rank;
import vo.Suit;

public class Deck {
    private final List<Card> cards;

    public Deck() {
        this.cards = makeCards();
    }

    public void shuffleCards() {
        Collections.shuffle(cards);
    }

    public Card drawCard() {
        return cards.removeFirst();
    }

    private List<Card> makeCards() {
        List<Card> cards = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(new Card(suit, rank));
            }
        }
        return cards;
    }
}
