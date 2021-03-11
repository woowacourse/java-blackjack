package blackjack.state;

import blackjack.domain.card.Card;

import java.util.ArrayList;
import java.util.List;

public class Cards {
    private List<Card> values;

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

    public Score score() {
        return checkAce(cardsScoreSum());
    }

    private Score cardsScoreSum() {
        return new Score(this.values.stream()
                .mapToInt(Card::getScore)
                .sum());
    }

    private Score checkAce(Score score) {
        if (this.values.stream()
                .anyMatch(Card::isAce) && score.isChangeAceNumber()) {
            return new Score(score.aceNumberChange());
        }
        return score;
    }
}
