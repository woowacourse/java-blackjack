package blackjack.factory;

import blackjack.domain.Dealer;
import blackjack.domain.Hand;

public final class DealerFactory {

    private DealerFactory() {
    }

    public static Dealer generate() {
        return new Dealer(new Hand());
    }
}
