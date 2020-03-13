package blackjack.domain.strategy;

import blackjack.domain.UserResult;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.User;

public interface DealerStatusStrategy {
    UserResult compute(Dealer dealer, User user);
}
