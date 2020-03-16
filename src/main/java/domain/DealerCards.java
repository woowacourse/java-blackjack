package domain;

import util.BlackJackRule;

public class DealerCards extends Cards {
    public boolean canHit() {
        return BlackJackRule.canDealerHit(getScore());
    }
}
