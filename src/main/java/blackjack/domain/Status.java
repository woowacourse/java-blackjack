package blackjack.domain;

import blackjack.domain.strategy.DealerBlackJackStrategy;
import blackjack.domain.strategy.DealerBustStrategy;
import blackjack.domain.strategy.DealerStatusHitableStrategy;
import blackjack.domain.strategy.DealerStatusStrategy;

public enum Status {
    BLACKJACK(new DealerBlackJackStrategy()),
    BUST(new DealerBustStrategy()),
    HIT_ABLE(new DealerStatusHitableStrategy());

    private DealerStatusStrategy dealerStatusStrategy;

    Status(DealerStatusStrategy dealerStatusStrategy) {
        this.dealerStatusStrategy = dealerStatusStrategy;
    }

    public DealerStatusStrategy getDealerStatusStrategy() {
        return dealerStatusStrategy;
    }
}
