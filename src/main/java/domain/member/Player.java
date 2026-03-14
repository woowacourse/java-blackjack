package domain.member;

import domain.state.State;
import domain.state.Stay;

public class Player extends Member {
    private final Money betMoney;

    public Player(String name, Money betMoney, State initialState) {
        super(name, initialState);
        this.betMoney = betMoney;
    }

    public void changeToStay() {
        state = new Stay(state.hand());
    }

    public int calculateProfit(Member member) {
        return betMoney.calculateProfit(state.earningRate(member.state));
    }
}
