package blackjack.domain.strategy;

import blackjack.domain.Dealer;
import blackjack.domain.Status;
import blackjack.domain.User;
import blackjack.domain.UserResult;

public class DealerBlackJackStrategy implements DealerStatusStrategy {
    @Override
    public UserResult compute(Dealer dealer, User user) {
        if (user.getStatus() == Status.BLACKJACK) {
            return UserResult.DRAW;
        }
        return UserResult.LOSE;
    }
}
