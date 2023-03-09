package domain.card;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CardHolder {
    public static final int ACE_SCORE_SWITCHING_LINE = 10;
    public static final int ACE_MAX_SCORE = 11;
    public static final int ACE_MIN_SCORE = 1;

    private final List<Card> cards;

    public CardHolder(List<Card> cards) {
        this.cards = cards;
    }

    public static CardHolder makeEmptyHolder() {
        return new CardHolder(new ArrayList<>());
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int getTotalScore() {
        int totalScore = getTotalScoreExceptAce();

        for (int aceCount = 0; aceCount < containsAce(); aceCount++) {
            totalScore = getTotalScoreContainingAce(totalScore);
        }

        return totalScore;
    }

    private int getTotalScoreExceptAce() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    private int containsAce() {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
    }

    private int getTotalScoreContainingAce(int totalScore) {
        if (totalScore <= ACE_SCORE_SWITCHING_LINE) {
            return totalScore + ACE_MAX_SCORE;
        }

        return totalScore + ACE_MIN_SCORE;
    }

    public boolean isBust() {
        return getTotalScore() > Card.BUST_DEADLINE;
    }

    public List<Card> getCards() {
        return cards.stream()
                .collect(Collectors.toUnmodifiableList());
    }

    public Card getCardIndexOf(int index) {
        return cards.get(index);
    }
}
