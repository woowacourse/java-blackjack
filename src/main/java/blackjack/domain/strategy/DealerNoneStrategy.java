package blackjack.domain.strategy;

import blackjack.domain.Dealer;
import blackjack.domain.Status;
import blackjack.domain.User;
import blackjack.domain.UserResult;

public class DealerNoneStrategy implements DealerStatusStrategy {
    @Override
    public UserResult compute(Dealer dealer, User user) {
        if (user.getStatus() == Status.BLACKJACK) {
            return UserResult.WIN;
        }
        if (user.getStatus() == Status.BUST) {
            return UserResult.LOSE;
        }
        return compareScore(dealer, user);
    }

    private UserResult compareScore(Dealer dealer, User user) {
        if (dealer.calculateScore() < user.calculateScore()) {
            return UserResult.WIN;
        }
        if (dealer.calculateScore() == user.calculateScore()) {
            return UserResult.DRAW;
        }
        return UserResult.LOSE;
    }
}