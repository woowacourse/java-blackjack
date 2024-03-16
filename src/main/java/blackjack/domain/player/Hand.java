package blackjack.domain.player;

import blackjack.domain.card.Card;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {
    private static final int ACE_ADDITIONAL_SCORE = 10;
    private static final int BUST_THRESHOLD = 21;
    private static final int BLACKJACK_SIZE = 2;

    private final List<Card> cards = new ArrayList<>();

    public int calculateScore() {
        final int initialScore = calculateInitialScore();
        final int sumWithAdditionalScore = initialScore + ACE_ADDITIONAL_SCORE;

        if (hasAceCard() && sumWithAdditionalScore <= BUST_THRESHOLD) {
            return sumWithAdditionalScore;
        }
        return initialScore;
    }

    private int calculateInitialScore() {
        return cards.stream()
                .mapToInt(card -> card.getNumber().getValue())
                .sum();
    }

    private boolean hasAceCard() {
        return cards.stream()
                .anyMatch(Card::isAceCard);
    }

    public void add(final Card card) {
        cards.add(card);
    }

    public boolean isBust() {
        return calculateScore() > BUST_THRESHOLD;
    }

    public boolean isBlackjack() {
        return cards.size() == BLACKJACK_SIZE && calculateScore() == BUST_THRESHOLD;
    }

    public List<Card> getAllCards() {
        return Collections.unmodifiableList(cards);
    }

}
