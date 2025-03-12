package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Hand {

    public static final int BURST_THRESHOLD = 21;
    private static final int ACE_SUBTRACT = 10;

    private final List<Card> hand;

    public Hand(final List<Card> hand) {
        this.hand = new ArrayList<>(hand);
    }

    public int calculateResult() {
        int maxScore = calculateMaxScore(hand);
        if (isNotBurst(maxScore)) {
            return maxScore;
        }
        return subtractAce(maxScore);
    }

    public int calculateWithSoftHand() {
        return hand.stream()
                .mapToInt(Card::getCardMinNumber)
                .sum();
    }

    public void addAll(final Hand givenHand) {
        hand.addAll(givenHand.getHand());
    }

    private int calculateMaxScore(final List<Card> cards) {
        return cards.stream()
                .mapToInt(Card::getCardMaxNumber)
                .sum();
    }

    private boolean isNotBurst(final int score) {
        return score <= BURST_THRESHOLD;
    }

    private int subtractAce(int score) {
        int aceCount = countAce(hand);
        while (!isNotBurst(score) && aceCount-- > 0) {
            score -= ACE_SUBTRACT;
        }
        return score;
    }

    private int countAce(final List<Card> cards) {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
    }

    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof final Hand hand1)) {
            return false;
        }
        return Objects.equals(hand, hand1.hand);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(hand);
    }

    public List<Card> getHand() {
        return Collections.unmodifiableList(hand);
    }

    public Hand getPartialCards(int start, int end) {
        return new Hand(hand.subList(start, end));
    }

    public Card getFirstCard() {
        return hand.getFirst();
    }

    public int getSize() {
        return hand.size();
    }
}
