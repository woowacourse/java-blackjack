package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {

    private final List<Card> cards;

    public Cards() {
        cards = new ArrayList<>();
    }

    public void add(final Card card) {
        cards.add(card);
    }

    public Score calculateScore() {
        Score score = Score.ZERO_SCORE;
        for (Card card : cards) {
            score = score.addScore(card.getScore());
        }
        return decideAceScore(score);
    }

    private Score decideAceScore(Score score) {
        if (hasAce() && isNotBust(score.useAceAsEleven())) {
            return score.useAceAsEleven();
        }
        return score;
    }

    private boolean hasAce() {
        return cards.stream()
                .filter(card -> card.isAce())
                .findFirst()
                .isPresent();
    }

    private boolean isNotBust(Score score) {
        return score.isBellowThanBlackJack();
    }

    public List<Card> cards() {
        return Collections.unmodifiableList(cards);
    }
}
