package blackJack.domain.participant;

import blackJack.domain.card.Score;

public class Dealer extends Participant {

    private static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(DEALER_NAME);
    }

    @Override
    public boolean hasNextTurn() {
        return new Score(this.getScore()).isPossibleDealerHit();
    }
}
