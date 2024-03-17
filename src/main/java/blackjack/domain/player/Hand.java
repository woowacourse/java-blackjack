package blackjack.domain.player;

import blackjack.domain.card.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static blackjack.domain.player.Player.BUST_CONDITION;

public class Hand {
    private static final int BONUS_SCORE = 10;
    private static final int NON_SCORE = 0;
    private final List<Card> cards = new ArrayList<>();

    Hand() {
    }

    int getScore() {
        final int minimumScore = cards.stream()
                .mapToInt(Card::getScore)
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

    boolean hasExactlyTwoCards() {
        return cards.size() == 2;
    }

    Card firstCard() {
        return cards.get(0);
    }
}
