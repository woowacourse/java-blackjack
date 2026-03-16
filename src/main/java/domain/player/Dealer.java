package domain.player;

import domain.vo.Cost;
import domain.vo.Name;

public class Dealer extends Participant {
    private static final int DEALER_STAY_THRESHOLD = 17;
    private static final int DEALER_INIT_COST = 0;

    public Dealer() {
        super(new Name("딜러"));
        setCost(new Cost(DEALER_INIT_COST));
    }

    public boolean isHit() {
        return getCardSum() < DEALER_STAY_THRESHOLD;
    }
}
