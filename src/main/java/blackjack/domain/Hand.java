package blackjack.domain;

import java.util.List;

public class Hand {

    public static final int INITIAL_COUNTS = 2;
    private final List<Card> hand;

    public Hand(List<Card> cards) {
        validateCardCounts(cards);
        this.hand = cards;
    }

    private void validateCardCounts(List<Card> cards) {
        if (cards.size() != INITIAL_COUNTS) {
            throw new IllegalArgumentException(String.format("초기 핸드는 %d장만 가질 수 있습니다.", INITIAL_COUNTS));
        }
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

    public void put(Card card) {
        hand.add(card);
    }
}
