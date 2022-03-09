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
        for (int i = 0; i < countAce(); i++) {
            if (sum <= 21) {
                break;
            }
            sum -= 10;
        }
        return sum;
    }

    private int countAce() {
        return (int) holdingCard.stream()
                .filter(card -> card.getCardNumber() == CardNumber.ACE)
                .count();
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
