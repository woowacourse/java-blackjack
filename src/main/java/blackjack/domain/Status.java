package blackjack.domain;

import blackjack.domain.strategy.DealerBlackJackStrategy;
import blackjack.domain.strategy.DealerBustStrategy;
import blackjack.domain.strategy.DealerStatusNoneStrategy;
import blackjack.domain.strategy.DealerStatusStrategy;

public enum Status {
    BLACKJACK(new DealerBlackJackStrategy()),
    BUST(new DealerBustStrategy()),
    NONE(new DealerStatusNoneStrategy());

    private DealerStatusStrategy dealerStatusStrategy;

    Status(DealerStatusStrategy dealerStatusStrategy) {
        this.dealerStatusStrategy = dealerStatusStrategy;
    }

    public DealerStatusStrategy getDealerStatusStrategy() {
        return dealerStatusStrategy;
    }
}
