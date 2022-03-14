package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class HoldingCards {
    private static final int BLACK_JACK_SCORE = 21;

    private final List<Card> cards;

    public HoldingCards(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public void add(Card card) {
        cards.add(card);
    }

    public boolean isBust() {
        return calculateTotal() > BLACK_JACK_SCORE;
    }

    private boolean isBlackJack() {
        return calculateTotal() == BLACK_JACK_SCORE;
    }

    public boolean isBlackJackOrBust() {
        return isBlackJack() || isBust();
    }

    public int calculateTotal() {
        int sum = sum();
        if (hasAce() && sum <= 11) {
            sum += 10;
        }
        return sum;
    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(card -> card.getCardNumberValue() == CardNumber.ACE.getCardNumberValue());
    }

    private int sum() {
        return cards.stream()
                .mapToInt(Card::getCardNumberValue)
                .sum();
    }

    public List<Card> getCards() {
        return cards;
    }

    public Card getFirstCard() {
        return cards.get(0);
    }
}
