package blackjack.domain.participant;

import blackjack.domain.state.Ready;

public class Dealer extends Participant {

    private static final Name DEALER_NAME = new Name("딜러");
    private static final int DEALER_DRAWABLE_SCORE = 16;

    public Dealer() {
        super(DEALER_NAME, new Ready());
    }

    @Override
    public boolean isDrawable() {
        return getTotalScore() <= DEALER_DRAWABLE_SCORE;
    }
}
