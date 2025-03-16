package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Hand {

    private static final int BUST_THRESHOLD = 22;
    private static final int BLACKJACK_SCORE = 21;
    private static final int BLACKJACK_SIZE = 2;
    private static final int ACE_BONUS = 10;

    private final List<Card> cards;

    private Hand(List<Card> cards) {
        this.cards = cards;
    }

    public static Hand empty() {
        return new Hand(new ArrayList<>());
    }

    public static Hand of(List<Card> cards) {
        return new Hand(cards);
    }

    public void add(Card card) {
        if (isBust() || isTwentyOne()) {
            throw new IllegalArgumentException("카드를 더 받을 수 없습니다.");
        }
        this.cards.add(card);
    }

    public int size() {
        return cards.size();
    }

    public int calculateFinalScore() {
        int totalScore = calculateTotalScore();
        if (hasAce()) {
            return applyBonus(totalScore);
        }
        return totalScore;
    }

    private int applyBonus(int totalScore) {
        if (isBust(totalScore + ACE_BONUS)) {
            return totalScore;
        }
        return totalScore + ACE_BONUS;
    }

    public boolean isBust() {
        return isBust(calculateTotalScore());
    }

    public boolean isBlackJack() {
        return calculateFinalScore() == BLACKJACK_SCORE && size() == BLACKJACK_SIZE;
    }

    public boolean isTwentyOne() {
        return calculateFinalScore() == BLACKJACK_SCORE;
    }

    private int calculateTotalScore() {
        return cards.stream().mapToInt(Card::getNumber).sum();
    }

    private boolean isBust(int totalScore) {
        return totalScore >= BUST_THRESHOLD;
    }

    private boolean hasAce() {
        return cards.stream().anyMatch(Card::isAce);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Hand hand = (Hand) o;
        return Objects.equals(cards, hand.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cards);
    }
}
