package blackjack.domain.user;

import blackjack.domain.result.Outcome;

public class Player extends User {

    private static final int BASES_SCORE_CAN_DRAW = 21;

    public Player(String name) {
        super(name, BASES_SCORE_CAN_DRAW);
    }

    public Outcome calculateOutcome(User dealer) {
        return Outcome.from(getScore(), dealer.getScore());
    }
}
