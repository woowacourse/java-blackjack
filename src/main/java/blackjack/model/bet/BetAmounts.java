package blackjack.model.bet;

import blackjack.model.user.User;
import java.util.Map;

public class BetAmounts {

    private final Map<User, BetAmount> betAmounts;

    public BetAmounts(Map<User, BetAmount> betAmounts) {
        this.betAmounts = Map.copyOf(betAmounts);
    }

    public BetAmount findByUser(User user) {
        return betAmounts.get(user);
    }
}
