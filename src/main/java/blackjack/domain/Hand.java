package blackjack.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {

    public static final int INITIAL_COUNTS = 2;
    private final List<Card> hand = new ArrayList<>();

    public int calculate() {
        int aceCounts = getAceCounts();

        return calculateCardNumber(aceCounts);
    }

    private int getAceCounts() {
        return (int) hand.stream()
                .filter(Card::isAce)
                .count();
    }

    private int calculateCardNumber(int aceCounts) {
        int sum = calculateWithDefaultAceNumber();
        for (int i = 0; i < aceCounts && !isAceAddable(sum); i++) {
            sum += 10;
        }
        return sum;
    }

    private int calculateWithDefaultAceNumber() {
        return hand.stream()
                .mapToInt(card -> card.getNumber().getValue())
                .sum();
    }

    private boolean isAceAddable(int sum) {
        return sum + 10 > 21;
    }

    public void initialize(List<Card> cards) {
        validateCardCounts(cards);
        hand.addAll(cards);
    }

    private void validateCardCounts(List<Card> cards) {
        if (cards.size() != INITIAL_COUNTS) {
            throw new IllegalArgumentException(String.format("초기 핸드는 %d장만 가질 수 있습니다.", INITIAL_COUNTS));
        }
    }

    public void put(Card card) {
        hand.add(card);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(hand);
    }
}
