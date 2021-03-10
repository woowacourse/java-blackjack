package blackjack.domain.card;

import java.util.List;

public class Deck {

    private final CardStack cards;

    private Deck(final CardStack cards) {
        this.cards = cards;
    }

    public static Deck create() {
        return new Deck(CardStack.create());
    }

    public List<Card> makeInitialHands() {
        return cards.pickTwoCards();
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public Card pick() {
        if (isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 덱의 카드가 모두 소진되었습니다.");
        }
        return cards.pick();
    }
}
