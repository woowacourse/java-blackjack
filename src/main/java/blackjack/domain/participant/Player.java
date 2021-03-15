package blackjack.domain.participant;

import blackjack.domain.Result;

public class Player extends Participant {

    private final Name name;
    private final BetAmount betAmount;

    public Player(final Name name, final BetAmount betAmount) {
        this.name = name;
        this.betAmount = betAmount;
    }

    public Result judgeByDealerState(final Dealer dealer) {
        return this.state.calculatePlayerResult(dealer.state);
    }

    public BetAmount profit(final Result result) {
        return this.betAmount.multiply(result.getAmplification());
    }

    public String getName() {
        return this.name.getValue();
    }
}
