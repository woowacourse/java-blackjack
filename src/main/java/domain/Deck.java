package domain;

import domain.constant.Rank;
import domain.constant.Suit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    List<Card> cards = new ArrayList<>();

    public Deck() {
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
        Collections.shuffle(cards);
    }

    private void init() {
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(new Card(rank, suit));
            }
        }
    }
}
