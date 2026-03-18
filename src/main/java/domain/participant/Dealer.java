package domain.participant;

import domain.Name;
import java.util.List;

public class Dealer extends Participant {
    private static final int DEALER_DRAW_CONDITION = 16;

    public Dealer() {
        super(new Name("딜러"));
    }

    @Override
    public boolean canDraw() {
        return getScore() <= DEALER_DRAW_CONDITION;
    }

    @Override
    public List<String> getInitialOpenCards() {
        return List.of(getOpenCard());
    }

    private String getOpenCard() {
        return getHandToString().getFirst();
    }
}