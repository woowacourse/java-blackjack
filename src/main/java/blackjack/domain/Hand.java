package blackjack.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Hand {
    private static final Score ACE_ADDITIONAL_SCORE = Score.from(10);

    private final List<Card> cards;

    private Hand(List<Card> cards) {
        this.cards = cards;
    }

    public static Hand from(Card... cards) {
        return new Hand(new ArrayList<>(Arrays.asList(cards)));
    }

    public Score calculate() {
        Score defaultScore = calculateDefaultScore();
        Score addedAceScore = defaultScore.add(ACE_ADDITIONAL_SCORE);
        if (hasAce() && addedAceScore.isNotBurst()) {
            return addedAceScore;
        }
        return defaultScore;
    }

    private Score calculateDefaultScore() {
        return cards.stream()
                .map(Card::getScore)
                .reduce(Score.from(0), Score::add);
    }

    private boolean hasAce() {
        return cards.stream().anyMatch(Card::isAce);
    }
}
