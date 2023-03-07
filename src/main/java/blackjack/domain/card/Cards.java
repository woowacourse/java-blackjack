package blackjack.domain.card;

import java.util.Collections;
import java.util.List;

public class Cards {

    private static final int BLACKJACK_SIZE_CONDITION = 2;
    private static final int BLACKJACK_SCORE_CONDITION = 21;
    private static final int MAKE_ACE_BIGGER_SCORE = 10;
    private static final int ACE_BIGGER_SCORE = BLACKJACK_SCORE_CONDITION - MAKE_ACE_BIGGER_SCORE;

    private final List<Card> cards;

    public Cards(final List<Card> cards) {
        this.cards = cards;
    }

    public Cards() {
        this.cards = Collections.emptyList();
    }

    public int calculateTotalScore() {
        int score = preCalculate();

        if (containsAce()) {
            score = plusScoreIfNotBust(score);
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

    private int plusScoreIfNotBust(int score) {
        if (score <= ACE_BIGGER_SCORE) {
            score += MAKE_ACE_BIGGER_SCORE;
        }

        return score;
    }

    private boolean containsAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    public void add(final Card card) {
        this.cards.add(card);
    }

    public boolean isBlackjack() {
        return cards.size() == BLACKJACK_SIZE_CONDITION && BLACKJACK_SCORE_CONDITION == calculateTotalScore();
    }

    public boolean isBust() {
        return BLACKJACK_SCORE_CONDITION < calculateTotalScore();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
