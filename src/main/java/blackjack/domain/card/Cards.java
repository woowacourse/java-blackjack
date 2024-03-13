package blackjack.domain.card;


import java.util.ArrayList;
import java.util.List;

public class Cards {
    private static final int BUST_SIZE = 21;
    private static final int CHANGE_A_VALUE = 10;
    private final List<Card> cards;

    public Cards(final List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public int calculate() {
        if (containAce() && isNotBust()) {
            return sum() + CHANGE_A_VALUE;
        }
        return sum();
    }

    public void add(final Card card) {
        cards.add(card);
    }

    private int sum() {
        return cards.stream()
                    .mapToInt(Card::getCardScore)
                    .sum();
    }

    private boolean containAce() {
        return cards.stream()
                    .anyMatch(Card::isAce);
    }

    private boolean isNotBust() {
        return sum() + CHANGE_A_VALUE <= BUST_SIZE;
    }

    public Card getFirstCard() {
        return cards.get(0);
    }

    public List<Card> toList() {
        return cards;
    }
}
