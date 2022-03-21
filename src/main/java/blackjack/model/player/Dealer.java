package blackjack.model.player;

import blackjack.model.bet.Result;
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
        Result result = compareByBlackjackWith(entry);
        if (result != null) {
            return result;
        }
        result = compareByBustWith(entry);
        if (result != null) {
            return result;
        }
        return compareScoreWith(entry);
    }

    private Result compareByBlackjackWith(Entry entry) {
        if (entry.isBlackjack() && this.isBlackjack()) {
            return Result.TIE;
        }
        if (entry.isBlackjack()) {
            return Result.BLACKJACK;
        }
        return null;
    }

    private Result compareByBustWith(Entry entry) {
        if (entry.isBust()) {
            return Result.LOSE;
        }
        if (this.isBust()) {
            return Result.WIN;
        }
        return null;
    }

    private Result compareScoreWith(Entry entry) {
        if (this.isScoreLessThen(entry)) {
            return Result.WIN;
        }
        if (entry.isScoreLessThen(this)) {
            return Result.LOSE;
        }
        return Result.TIE;
    }

    public void hit(TrumpCard card) {
        if (canHit()) {
            this.addCard(card);
        }
    }
}
