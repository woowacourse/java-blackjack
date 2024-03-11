package domain.player;

import domain.blackjack.OneOnOneResult;

public class Dealer extends Player {
    private static final int HIT_UPPER_BOUND = 17;
    private static final Dealer dealer = new Dealer();

    private Dealer() {
        super(new Name(""));
    }

    public static Dealer getInstance() {
        return dealer;
    }

    @Override
    public boolean canHit() {
        return calculateScore() < HIT_UPPER_BOUND;
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
