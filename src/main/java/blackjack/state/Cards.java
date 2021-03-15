package blackjack.state;

import blackjack.domain.card.Card;

import java.util.ArrayList;
import java.util.List;

public class Cards {
    private static final int DEALER_REDRAW_STANDARD = 17;

    private final List<Card> values;

    public Cards(final List<Card> values) {
        this.values = new ArrayList<>(values);
    }

    public List<Card> list() {
        return new ArrayList<>(values);
    }

    public void add(Card card) {
        values.add(card);
    }

    public boolean isBust() {
        return score().isBust();
    }

    public boolean isBlackJack() {
        return score().isBlackJack() && values.size() == 2;
    }

    public Score score() {
        return cardsScoreSum();
    }

    private Score cardsScoreSum() {
        Score score = new Score(this.values.stream()
                .mapToInt(Card::getScore)
                .sum());

        if (this.values.stream()
                .anyMatch(Card::isAce) && score.isChangeAceNumber()) {
            return new Score(score.aceNumberChange());
        }

        return score;
    }

    public boolean isOverDrawScore() {
        return this.score().getScore() >= DEALER_REDRAW_STANDARD;
    }
}
