package domain.deck;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;

import java.util.ArrayList;
import java.util.List;

public class Deck {
    private final Shuffle shuffle;
    List<Card> cards = new ArrayList<>();

    public Deck(Shuffle shuffle) {
        this.shuffle = shuffle;
        init();
    }

    public int size() {
        return cards.size();
    }

    public Card draw() {
        if (size() == 0) {
            throw new IllegalArgumentException("남아있는 카드가 없습니다.");
        }
        return cards.removeFirst();
    }

    public void shuffle() {
        shuffle.shuffle(cards);
    }

    private void init() {
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(new Card(rank, suit));
            }
        }
    }
}
