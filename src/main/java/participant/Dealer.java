package participant;

import game.GameRule;

public class Dealer extends Participant {
    private static final String NAME = "딜러";

    public Dealer() {
        super();
    }

    @Override
    public boolean ableToDraw() {
        return getScore() <= GameRule.DEALER_STAY.getValue();
    }

    @Override
    public boolean isDealer() {
        return true;
    }

    @Override
    public String getNickname() {
        return NAME;
    }

}
