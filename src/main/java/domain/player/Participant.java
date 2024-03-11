package domain.player;

import domain.blackjack.OneOnOneResult;

public class Participant extends Player {
    private static final int HIT_UPPER_BOUND = 21;

    public Participant(final Name name) {
        super(name);
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
        return calculateScore() < HIT_UPPER_BOUND;
    }

    @Override
    public boolean canNotHit() {
        return !canHit();
    }

    @Override
    public OneOnOneResult determineWinnerByComparing(final Player dealer) {
        if(isBust()){
            return OneOnOneResult.ONE_LOSE;
        }
        if (dealer.isBust()) {
            return OneOnOneResult.ONE_WIN;
        }
        return OneOnOneResult.fromCondition(calculateScore() > dealer.calculateScore());
    }
}
