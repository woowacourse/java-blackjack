package blackjack.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {

    private static final int MAKE_ACE_BIGGER_SCORE = 10;
    //private static final int BURST_SCORE = 21;
    private static final int BLACKJACK_SIZE_CONDITION = 2;
    private static final int BLACKJACK_SCORE_CONDITION = 21;

    private final List<Card> cards;

    public Cards(final List<Card> cards) {
        this.cards = cards;
    }

    public static Cards generateEmptyCards() {
        return new Cards(new ArrayList<>());
    }

    public int calculateTotalScore() {
        int score = preCalculate();

        if (containsAce()) {
            score = plusScoreIfNotBurst(score);
        }

        return score;
    }

    private int preCalculate() {
        int score = 0;
        for (Card card : cards) {
            score += card.convertToScore();
        }

        return score;
    }

    private int plusScoreIfNotBurst(int score) {
        if (score + MAKE_ACE_BIGGER_SCORE <= BLACKJACK_SCORE_CONDITION) {
            score += MAKE_ACE_BIGGER_SCORE;
        }

        return score;
    }

    private boolean containsAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    public void add(Card card) {
        this.cards.add(card);
    }

    public boolean isBlackjack() {
        return cards.size() == BLACKJACK_SIZE_CONDITION
                && calculateTotalScore() == BLACKJACK_SCORE_CONDITION;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
