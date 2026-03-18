package blackjack.domain.deck;

import blackjack.domain.card.Card;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    private final List<Card> cards;

    private Deck(List<Card> cards) {
        this.cards = cards;
    }

    public static Deck createWithShuffled() {
        List<Card> copy = new ArrayList<>(Card.getAllCards());
        Collections.shuffle(copy);

        return new Deck(copy);
    }

    public Card pop() {
        if (hasNext()) {
            return cards.removeFirst();
        }
        throw new IllegalStateException("덱에 더 이상의 카드가 없습니다.");
    }

    private boolean hasNext() {
        return !cards.isEmpty();
    }

}
