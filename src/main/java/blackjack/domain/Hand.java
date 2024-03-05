package blackjack.domain;

import java.util.List;

public class Hand {

    private final List<Card> hand;

    public Hand(List<Card> cards) {
        this.hand = cards;
    }

    public int calculate() {
        int sum = hand.stream()
                .mapToInt(card -> card.getNumber().getValue())
                .sum();

        long aceCounts = hand.stream()
                .filter(Card::isAce)
                .count();

        for (int i = 0; i < aceCounts; i++) {
            if (sum + 10 > 21) {
                break;
            }

            sum += 10;
        }
        return sum;
    }
}
