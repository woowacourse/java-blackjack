package blackjack.blackjack.card;

import blackjack.util.ExceptionMessage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class Hand {

    private static final int BLACKJACK_SIZE = 2;
    public static final int BURST_THRESHOLD = 21;
    private static final int ACE_SUBTRACT = 10;

    private final List<Card> cards;

    public Hand(final List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public Hand(final Card card) {
        this(List.of(card));
    }

    public int calculateScore() {
        int maxScore = calculateMaxScore(cards);
        if (isNotBurst(maxScore)) {
            return maxScore;
        }
        return subtractAce(maxScore);
    }

    public int calculateWithHardHand() {
        return cards.stream()
                .mapToInt(Card::getCardMinNumber)
                .sum();
    }

    public void add(final Card card) {
        cards.add(card);
    }

    public void addAll(final Hand givenHand) {
        cards.addAll(givenHand.getCards());
    }

    public boolean isBlackjack() {
        return cards.size() == BLACKJACK_SIZE && calculateScore() == BURST_THRESHOLD;
    }

    public boolean isBust() {
        return calculateScore() > BURST_THRESHOLD;
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
        int aceCount = countAce(cards);
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

    private void validateIndex(final int start, final int end) {
        final int size = cards.size();
        if (start < 0 || end < 0 || start >= size || end > size) {
            throw new IllegalArgumentException(ExceptionMessage.makeMessage("인덱스는 0 이상 hand 크기 이하여야 합니다"));
        }
        if (start > end) {
            throw new IllegalArgumentException(ExceptionMessage.makeMessage("끝 인덱스는 시작 인덱스보다 커야합니다"));
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof final Hand hand1)) {
            return false;
        }
        return Objects.equals(cards, hand1.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cards);
    }

    public int getSize() {
        return cards.size();
    }

    public Card getFirstCard() {
        return cards.getFirst();
    }

    public Hand getPartialHand(final int startInclusive, final int endExclusive) {
        validateIndex(startInclusive, endExclusive);
        return new Hand(cards.subList(startInclusive, endExclusive));
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
