package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class HoldingCard {
    private static final int BLACK_JACK_SCORE = 21;
    private final List<Card> holdingCard;

    public HoldingCard(List<Card> cards) {
        this.holdingCard = new ArrayList<>();
        holdingCard.addAll(cards);
    }

    public void add(Card card) {
        holdingCard.add(card);
    }

    public boolean isBust() {
        return calculateTotal() > BLACK_JACK_SCORE;
    }

    public int calculateTotal() {
        int sum = sum();
        if (hasAce() && sum <= 11) {
            sum += 10;
        }
        return sum;
    }

    private boolean hasAce() {
        return holdingCard.stream()
                .anyMatch(card -> card.getCardNumberValue() == CardNumber.ACE.getCardNumberValue());
    }

    private int sum() {
        return holdingCard.stream()
                .mapToInt(Card::getCardNumberValue)
                .sum();
    }

    public List<Card> getHoldingCard() {
        return holdingCard;
    }

    public Card getFirstCard() {
        return holdingCard.get(0);
    }
}
