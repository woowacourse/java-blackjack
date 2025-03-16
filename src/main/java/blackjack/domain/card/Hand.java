package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class Hand {

    public static final int BURST_THRESHOLD = 21;
    private static final int ACE_SUBTRACT = 10;
    private static final int BLACKJACK_SIZE = 2;

    private final List<Card> hand;

    public Hand(final List<Card> hand) {
        this.hand = new ArrayList<>(hand);
    }

    public static Hand from(final Card card) {
        return new Hand(List.of(card));
    }

    public int calculateResult() {
        int maxScore = calculateMaxScore(hand);
        if (isNotBurst(maxScore)) {
            return maxScore;
        }
        return subtractAce(maxScore);
    }

    public int calculateWithHardHand() {
        return hand.stream()
                .mapToInt(Card::getCardMinNumber)
                .sum();
    }

    public void add(final Card card) {
        hand.add(card);
    }

    public void addAll(final Hand givenHand) {
        hand.addAll(givenHand.getHand());
    }

    public boolean isBlackjack() {
        return hand.size() == BLACKJACK_SIZE && calculateResult() == BURST_THRESHOLD;
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

    public Hand getPartialCards(int startInclusive, int endExclusive) {
        validateIndex(startInclusive, endExclusive);
        return new Hand(hand.subList(startInclusive, endExclusive));
    }

    private void validateIndex(final int start, final int end) {
        if (start < 0 || end < 0 || start >= hand.size() || end > hand.size()) {
            throw new IllegalArgumentException("[ERROR] 인덱스는 0 이상 hand 크기 이하여야 합니다");
        }
        if (start > end) {
            throw new IllegalArgumentException("[ERROR] 끝 인덱스는 시작 인덱스보다 커야합니다");
        }
    }

    public int getSize() {
        return hand.size();
    }

    public Card getFirstCard() {
        return hand.getFirst();
    }

    public List<Card> getHand() {
        return Collections.unmodifiableList(hand);
    }
}
