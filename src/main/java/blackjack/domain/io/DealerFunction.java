package blackjack.domain.io;

import blackjack.domain.user.Dealer;

@FunctionalInterface
public interface DealerFunction {

    void execute(Dealer dealer);
}
