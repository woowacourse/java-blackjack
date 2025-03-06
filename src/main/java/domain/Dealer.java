package domain;

import java.util.Comparator;
import java.util.List;

public class Dealer implements Participant {

    public static final int BUST_STANDARD = 21;
    public static final int DEALER_HIT_THRESHOLD = 16;

    private final List<Card> cards;

    public Dealer(List<Card> cards) {
        this.cards = cards;
    }

    public Card openOneCard() {
        return cards.stream()
                .min(Comparator.comparingInt(card -> card.getNumber().getValue()))
                .orElse(cards.getFirst());
    }

    public boolean isSumUnderSixteen() {
        return sumCardNumbers() <= DEALER_HIT_THRESHOLD;
    }

    @Override
    public boolean addOneCard(Card card) {
        cards.add(card);
        return sumCardNumbers() <= BUST_STANDARD;
    }

    @Override
    public int sumCardNumbers() {
        int aceCount = (int) cards.stream()
                .filter(card -> card.getNumber() == TrumpNumber.ACE)
                .count();

        int sum = cards.stream()
                .mapToInt(card -> card.getNumber().getValue())
                .sum();

        for (int i = 0; i < aceCount; i++) {
            sum = processAce(sum);
        }
        return sum;
    }

    private int processAce(int sum) {
        if (sum > BUST_STANDARD) {
            sum -= 10;
        }
        return sum;
    }

}
