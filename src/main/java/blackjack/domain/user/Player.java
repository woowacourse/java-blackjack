package blackjack.domain.user;

import blackjack.domain.result.Result;

public class Player extends User {
    public Player(String name) {
        super(name);
    }

    public Result produceResult(User dealer) {
        return Result.decide(this, dealer);
    }

    public long getProfit() {
        return -1000;
    }
}
