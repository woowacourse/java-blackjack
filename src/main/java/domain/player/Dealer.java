package domain.player;

import domain.blackjack.OneOnOneResult;

public class Dealer extends Player {

    private static final int HIT_THRESHOLD = 17;

    public Dealer() {
        super(Name.DEALER_NAME);
    }

    @Override
    public boolean isBust() {
        return calculateScore() > PERFECT_SCORE;
    }

    @Override
    public boolean isNotBust() {
        return !isBust();
    }

    @Override
    public boolean canHit() {
        return calculateScore() < HIT_THRESHOLD;
    }

    @Override
    public boolean canNotHit() {
        return false;
    }

    @Override
    public OneOnOneResult determineWinnerByComparing(final Player participant) {
        if (isBust()) {
            return OneOnOneResult.ONE_LOSE;
        }
        if (participant.isBust()) {
            return OneOnOneResult.ONE_WIN;
        }
        return OneOnOneResult.fromCondition(calculateScore() > participant.calculateScore());
    }
}
