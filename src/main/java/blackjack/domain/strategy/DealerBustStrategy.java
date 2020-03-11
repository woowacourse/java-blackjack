package blackjack.domain.strategy;

import blackjack.domain.Dealer;
import blackjack.domain.Status;
import blackjack.domain.User;
import blackjack.domain.UserResult;

public class DealerBustStrategy implements DealerStatusStrategy {
    @Override
    public UserResult compute(Dealer dealer, User user) {
        if (user.getStatus() == Status.BUST) {
            return UserResult.LOSE;
        }
        return UserResult.WIN;
    }
}
