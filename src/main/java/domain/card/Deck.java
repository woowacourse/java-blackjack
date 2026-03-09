package domain.card;

import domain.Rank;
import domain.Suit;
import java.util.ArrayList;
import java.util.List;

public class Deck {
    private final Cards cards;

    public Deck() {
        this.cards = new Cards();
        init();
        cards.shuffle();
    }

    public Card pull() {
        return cards.pull();
    }

    private void init() {
        for (Suit suit : Suit.values()) {
            addAllCardBySuit(suit);
        }
    }

    private void addAllCardBySuit(Suit suit) {
        for (Rank rank : Rank.values()) {
            cards.add(new Card(suit, rank));
        }
    }

    public List<Card> pullCards(int quantity) {
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            cards.add(pull());
        }
        return cards;
    }
}
