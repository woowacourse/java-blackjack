package blackjack.domain.player;

import static blackjack.domain.Rule.DEALER_HIT_STANDARD_SCORE;

public class Dealer extends Player {

    private static final String NAME = "딜러";

    public Dealer() {
        super(NAME);
    }

    @Override
    public boolean canTakeCard() {
        return getTotalScore() <= DEALER_HIT_STANDARD_SCORE.getValue();
    }
}
