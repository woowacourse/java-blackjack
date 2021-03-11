package blackjack.domain.participant;

import blackjack.domain.state.State;

public abstract class Participant {
    protected State state;
    protected final Name name;
    protected final BattingMoney battingMoney;

    protected Participant(String name, int money) {
        this.name = new Name(name);
        this.battingMoney = new BattingMoney(money);
    }
}
