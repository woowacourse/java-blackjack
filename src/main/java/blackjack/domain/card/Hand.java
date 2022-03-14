package blackjack.domain.card;

import java.util.Collections;
import java.util.List;

public class Hand {

    private static final int ACE_ADDITIONAL_VALUE = 10;
    private static final int TWENTY_ONE = 21;

    private final List<Card> hand;

    public Hand(List<Card> hand) {
        this.hand = hand;
    }

    public void add(Card card) {
        hand.add(card);
    }

    public List<Card> get() {
        return Collections.unmodifiableList(hand);
    }

    public int getTotalScore() {
        int sum = hand.stream()
                .mapToInt(value -> value.getCardNumber().getValue())
                .sum();

        for (int i = 0; i < countAce(); i++) {
            sum += getAceAdditionalValue(sum);
        }
        return sum;
    }

    private int countAce() {
        return (int) hand.stream()
                .filter(card -> card.getCardNumber().equals(CardNumber.ACE))
                .count();
    }

    private int getAceAdditionalValue(int sum) {
        if (sum + ACE_ADDITIONAL_VALUE <= TWENTY_ONE) {
            return ACE_ADDITIONAL_VALUE;
        }
        return 0;
    }
}
