package blackjack.domain;

import blackjack.domain.strategy.DealerBlackJackStrategy;
import blackjack.domain.strategy.DealerBustStrategy;
import blackjack.domain.strategy.DealerNoneStrategy;
import blackjack.domain.strategy.DealerStatusStrategy;

public enum Status {
    BLACKJACK(new DealerBlackJackStrategy()),
    BUST(new DealerBustStrategy()),
    NONE(new DealerNoneStrategy());

    private DealerStatusStrategy dealerStatusStrategy;

    Status(DealerStatusStrategy dealerStatusStrategy) {
        this.dealerStatusStrategy = dealerStatusStrategy;
    }

    public DealerStatusStrategy getDealerStatusStrategy() {
        return dealerStatusStrategy;
    }
}
