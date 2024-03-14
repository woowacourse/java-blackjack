package blackjack.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Deck {

    private final ShuffleStrategy shuffleStrategy;

    private final List<Card> cards;

    public Deck(List<Card> cards, ShuffleStrategy shuffleStrategy) {
        this.cards = new ArrayList<>(cards);
        this.shuffleStrategy = shuffleStrategy;
    }

    public void shuffle() {
        shuffleStrategy.shuffle(cards);
    }

    public Card draw() {
        if (cards.isEmpty()) {
            throw new NoSuchElementException("덱에 카드가 존재하지 않습니다.");
        }
        return cards.remove(cards.size() - 1);
    }
}
