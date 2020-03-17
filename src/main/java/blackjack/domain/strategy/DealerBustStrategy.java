package blackjack.domain.strategy;

import blackjack.domain.Status;
import blackjack.domain.UserResult;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.User;

public class DealerBustStrategy implements DealerStatusStrategy {
    @Override
    public UserResult compute(Dealer dealer, User user) {
        if (user.getStatus() == Status.BUST) {
            return UserResult.LOSE;
        }
        return UserResult.WIN;
    }
}
