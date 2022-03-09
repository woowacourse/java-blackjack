package blackjack.domain;

import java.util.ArrayList;
import java.util.Collection;

public class Cards {

    private static final int BLACKJACK_SCORE = 21;

    private final Collection<Card> cards = new ArrayList<>();

    public void add(Card card) {
        cards.add(card);
    }

    public int getScore() {
        int score = cards.stream()
                .mapToInt(Card::getValue)
                .sum();

        int aceCount = (int) cards.stream()
                .filter(Card::isAce)
                .count();

        score = handleAce(score, aceCount);
        return score;
    }

    public boolean isBust() {
        return getScore() > BLACKJACK_SCORE;
    }

    private int handleAce(int score, int aceCount) {
        while (isAbleToAddAdditionalAceValue(score, aceCount)) {
            score += Card.ADDITIONAL_ACE_VALUE;
            aceCount--;
        }
        return score;
    }

    private boolean isAbleToAddAdditionalAceValue(int score, int aceCount) {
        int expectedScore = score + Card.ADDITIONAL_ACE_VALUE;
        return aceCount > 0 && expectedScore <= BLACKJACK_SCORE;
    }
}
