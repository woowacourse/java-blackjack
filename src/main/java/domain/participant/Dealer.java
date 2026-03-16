package domain.participant;

import domain.Name;

public class Dealer extends Participant {
    private static final int DEALER_DRAW_CONDITION = 16;

    public Dealer() {
        super(new Name("딜러"));
    }

    @Override
    public boolean canDraw() {
        return getScore() <= DEALER_DRAW_CONDITION;
    }

    public String getOpenCard() {
        return getHandToString().getFirst();
    }
}