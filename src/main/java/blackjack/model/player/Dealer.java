package blackjack.model.player;

import blackjack.model.Result;
import blackjack.model.trumpcard.TrumpCard;

public class Dealer extends Player {
    private static final String NAME = "딜러";
    private static final int SCORE_HIT_CRITERIA = 17;

    public Dealer() {
        super(NAME);
    }

    public boolean canHit() {
        return this.deck.isScoreLessThan(SCORE_HIT_CRITERIA);
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

    public int getDeckSize() {
        return this.deck.getSize();
    }

    @Override
    public boolean isBust() {
        return this.deck.isBust();
    }

    @Override
    public void hit(TrumpCard card) {
        if (canHit()) {
            this.deck.add(card);
        }
    }
}
