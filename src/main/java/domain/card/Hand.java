package domain.card;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Hand {
    public static final int ACE_SCORE_SWITCHING_LINE = 10;
    public static final int ACE_MAX_SCORE = 11;
    public static final int ACE_MIN_SCORE = 1;
    public static final int BLACKJACK_SCORE = 21;

    private final List<Card> cards;

    public Hand(List<Card> cards) {
        this.cards = cards;
    }

    public static Hand withEmptyHolder() {
        return new Hand(new ArrayList<>());
    }

    public void draw(Card card) {
        cards.add(card);
    }

    public int getScore() {
        int score = getScoreExceptAce();

        for (int aceCount = 0; aceCount < containsAce(); aceCount++) {
            score = getScoreContainingAce(score);
        }

        return score;
    }

    private int getScoreExceptAce() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    private int containsAce() {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
    }

    private int getScoreContainingAce(int score) {
        if (score <= ACE_SCORE_SWITCHING_LINE) {
            return score + ACE_MAX_SCORE;
        }

        return score + ACE_MIN_SCORE;
    }

    public boolean isBust() {
        return getScore() > Card.BUST_DEADLINE;
    }

    public List<Card> getCards() {
        return cards.stream()
                .collect(Collectors.toUnmodifiableList());
    }

    public Card getFirstCard() {
        return cards.get(0);
    }

    public boolean isBlackJack() {
        return cards.size() == 2 && BLACKJACK_SCORE == getScore();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hand hand = (Hand) o;
        return cards.equals(hand.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cards);
    }

    @Override
    public String toString() {
        return "Hand{" +
                "cards=" + cards +
                '}';
    }
}
