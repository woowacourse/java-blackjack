package blackjack.domain.player;

import blackjack.domain.card.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {
    private static final int BUST_CONDITION = 21;
    private static final int BONUS_SCORE = 10;
    private static final int NON_SCORE = 0;

    private final List<Card> cards = new ArrayList<>();

    int getScore() {
        final int minimumScore = cards.stream()
                .mapToInt(Card::getNumber)
                .sum();

        final int bonusScore = this.getBonusScore();

        if (minimumScore + bonusScore <= BUST_CONDITION) {
            return minimumScore + bonusScore;
        }
        return minimumScore;
    }

    private int getBonusScore() {
        if (this.cards.stream().anyMatch(Card::isAceCard)) {
            return BONUS_SCORE;
        }
        return NON_SCORE;
    }

    void add(final Card card) {
        cards.add(card);
    }

    List<Card> getAllCards() {
        return Collections.unmodifiableList(cards);
    }
}
