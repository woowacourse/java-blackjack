package domain.player;

import domain.vo.Cost;
import domain.vo.Name;

public class Dealer extends Participant {
    private static final int DEALER_STAY_THRESHOLD = 17;

    public Dealer() {
        super(new Name("딜러"));
        setCost(new Cost(0));
    }

    public boolean isHit() {
        return getCardSum() < DEALER_STAY_THRESHOLD;
    }
}
