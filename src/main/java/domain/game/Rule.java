package domain.game;

import domain.card.Cards;
import domain.score.Status;

public class Rule {

    private static final int STANDARD = 21;

    public Rule() {
    }

    public Status decideStatus(Cards targetCards, Cards otherCards) {
        if (targetCards.bestSum() > STANDARD) {
            return Status.LOSE;
        }

        if (otherCards.bestSum() > STANDARD) {
            return Status.WIN;
        }

        if (otherCards.bestSum() < targetCards.bestSum()) {
            return Status.WIN;
        }

        if (otherCards.bestSum() > targetCards.bestSum()) {
            return Status.LOSE;
        }
        return Status.TIE;
    }
}
