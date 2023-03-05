package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {

    private static final int MAKE_ACE_BIGGER_SCORE = 10;
    private static final int BLACKJACK_SCORE_CONDITION = 21;
    private static final int BLACKJACK_SIZE_CONDITION = 2;

    private final List<Card> cards;

    public Cards(final List<Card> cards) {
        this.cards = cards;
    }

    public static Cards generateEmptyCards() {
        return new Cards(new ArrayList<>());
    }

    public int calculateScoreForBlackjack() {
        int score = calculate();

        if (containsAce()) {
            score = reCalculateIfSoftHand(score);
        }

        return score;
    }

    private int calculate() {
        int score = 0;

        for (Card card : cards) {
            score += card.convertToBlackjackScore();
        }

        return score;
    }

    private boolean containsAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    private int reCalculateIfSoftHand(int score) {
        if (score + MAKE_ACE_BIGGER_SCORE <= BLACKJACK_SCORE_CONDITION) {
            return score + MAKE_ACE_BIGGER_SCORE;
        }

        return score;
    }

    public void add(Card card) {
        this.cards.add(card);
    }

    public boolean isBlackjack() {
        return cards.size() == BLACKJACK_SIZE_CONDITION
                && calculateScoreForBlackjack() == BLACKJACK_SCORE_CONDITION;
    }

    public boolean isBust() {
        return calculateScoreForBlackjack() > BLACKJACK_SCORE_CONDITION;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
