package blackjack.domain;

import blackjack.domain.game.Result;
import blackjack.domain.user.Name;

import java.util.Map;

public class Settlement {

    private final Map<Name, Stake> userPrize;

    public Settlement(Map<Name, Stake> userPrize){
        this.userPrize = Map.copyOf(userPrize);
    }

    public int getProfit(final Name name, final Result result) {
        final Stake stake = userPrize.get(name);
        return result.getProfit(stake.getStake());
    }
}
