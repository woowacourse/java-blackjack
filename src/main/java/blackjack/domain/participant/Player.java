package blackjack.domain.participant;

import blackjack.domain.Result;

public class Player extends Participant {

    private final Name name;

    public Player(final Name name) {
        this.name = name;
    }

    public Result judgeByDealerState(final Dealer dealer) {
        return this.state.calculatePlayerResult(dealer.state);
    }

    public String getName() {
        return this.name.getValue();
    }
}
