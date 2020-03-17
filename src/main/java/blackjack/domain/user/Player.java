package blackjack.domain.user;

import blackjack.domain.result.Outcome;

public class Player extends User {

    private static final int BASES_SCORE_CAN_DRAW = 21;

    public Player(String name) {
        super(name);
    }

    public Outcome calculateOutcome(User dealer) {
        return Outcome.of(getScore(), dealer.getScore());
    }

    @Override
    public boolean canDrawCard() {
        return getScore().isEqualOrUnderScore(BASES_SCORE_CAN_DRAW);
    }
}
