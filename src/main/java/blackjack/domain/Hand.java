package blackjack.domain;

import java.util.List;

public class Hand {

    private final List<Card> hand;

    public Hand(List<Card> cards) {
        this.hand = cards;
    }

    public int calculate() {
        int aceCounts = getAceCounts();

        return calculateCardNumber(aceCounts);
    }

    private int calculateCardNumber(int aceCounts) {
        int sum = calculateWithDefaultAceNumber();
        for (int i = 0; i < aceCounts && isBurst(sum); i++) {
            sum += 10;
        }
        return sum;
    }

    private int getAceCounts() {
        return (int) hand.stream()
                .filter(Card::isAce)
                .count();
    }

    private int calculateWithDefaultAceNumber() {
        return hand.stream()
                .mapToInt(card -> card.getNumber().getValue())
                .sum();
    }

    private boolean isBurst(int sum) {
        return sum + 10 > 21;
    }
}
