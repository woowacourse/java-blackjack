package blackjack.model.player;

import blackjack.model.Result;
import blackjack.model.trumpcard.TrumpCard;

public final class Dealer extends Player {
    private static final String NAME = "딜러";
    private static final int SCORE_HIT_CRITERIA = 17;

    public Dealer() {
        super(NAME);
    }

    public boolean canHit() {
        return this.canHit(SCORE_HIT_CRITERIA);
    }

    public Result compareWith(Entry entry) {
        return new Result(entry, !isWinTo(entry));
    }

    private boolean isWinTo(Entry entry) {
        if (entry.isBust()) {
            return true;
        }
        if (this.isBust()) {
            return false;
        }
        return entry.getScore() < this.getScore();
    }

    public void hit(TrumpCard card) {
        if (canHit()) {
            this.addCard(card);
        }
    }
}
