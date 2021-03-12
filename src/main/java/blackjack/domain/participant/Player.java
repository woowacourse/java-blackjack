package blackjack.domain.participant;

import blackjack.domain.Result;

public class Player extends Participant {

    private final Name name;
    private BetAmount betAmount;

    public Player(final Name name) {
        this.name = name;
    }

    public void betting(final int betAmount) {
        this.betAmount = new BetAmount(betAmount);
    }

    public Result judgeByDealerState(final Dealer dealer) {
        return this.state.calculatePlayerResult(dealer.state);
    }

    public double profit(final Result result) {
        return this.betAmount.multiply(result.getAmplification());
    }

    public String getName() {
        return this.name.getValue();
    }
}
