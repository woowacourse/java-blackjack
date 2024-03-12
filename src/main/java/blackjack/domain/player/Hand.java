package blackjack.domain.player;

import blackjack.domain.card.Card;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {
    public static final int ACE_ADDITIONAL_SCORE = 10;
    public static final int BUST_THRESHOLD = 21;    // TODO: BUST_THRESHOLD 가 분산되어 있음

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
                .mapToInt(Card::getNumber)
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

    public List<Card> getAllCards() {
        return Collections.unmodifiableList(cards);
    }
}
