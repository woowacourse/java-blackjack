package blackjack_statepattern.participant;

import blackjack_statepattern.state.State;

public final class Player extends Participant {
    private final BetMoney betMoney;

    public Player(String name, BetMoney betMoney) {
        super(name);
        this.betMoney = betMoney;
    }

    public double profit(State dealerState) {
        return state.profit(dealerState, betMoney.getAmount());
    }

}
