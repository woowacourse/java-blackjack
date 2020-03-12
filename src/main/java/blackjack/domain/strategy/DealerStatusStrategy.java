package blackjack.domain.strategy;

import blackjack.domain.Dealer;
import blackjack.domain.User;
import blackjack.domain.UserResult;

public interface DealerStatusStrategy {
    UserResult compute(Dealer dealer, User user);
}
