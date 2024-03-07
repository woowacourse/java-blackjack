package domain;

public class Rule {

    private static final int STANDARD = 21;

    public Status decideStatus(Cards targetCards, Cards otherCards) {
        if (targetCards.sum() > STANDARD) {
            return Status.LOSE;
        }

        if (otherCards.sum() > STANDARD) {
            return Status.WIN;
        }

        if (otherCards.sum() < targetCards.sum()) {
            return Status.WIN;
        }

        if (otherCards.sum() > targetCards.sum()) {
            return Status.LOSE;
        }
        return Status.TIE;
    }
}
