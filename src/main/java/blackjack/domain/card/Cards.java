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

    public Score getScore() {
        Score score = Score.ZERO_SCORE;
        for (Card card : cards) {
            score = score.addScore(card.getDenomination().getScore());
        }

        if (countAce() != 0 && score.useAceAsEleven().isBellowThanBlackJack()) {
            score = score.useAceAsEleven();
        }
        return score;
    }

    public int countAce() {
        return (int) cards.stream()
                .filter(card -> card.isAce())
                .count();
    }

    public List<Card> cards() {
        return Collections.unmodifiableList(cards);
    }
}
