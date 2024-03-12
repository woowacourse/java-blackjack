package blackjack.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Hand {
    private static final Score ACE_ADDITIONAL_SCORE = Score.from(10);
    private static final Score NO_ADDITIONAL_SCORE = Score.from(0);

    private final List<Card> cards;

    public Hand(Card... cards) {
        this.cards = new ArrayList<>(Arrays.asList(cards));
    }

    public Score calculate() {
        if (hasAce()) {
            return calculateScore(ACE_ADDITIONAL_SCORE);
        }
        return calculateScore(NO_ADDITIONAL_SCORE);
    }

    private Score calculateScore(Score additionalScore) {
        return cards.stream()
                .map(Card::getScore)
                .reduce(additionalScore, Score::add);
    }

    private boolean hasAce() {
        return cards.stream().anyMatch(Card::isAce);
    }
}
