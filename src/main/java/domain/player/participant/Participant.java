package domain.player.participant;

import domain.player.Name;
import domain.player.Player;
import domain.player.participant.betresult.BetResultState;

public class Participant extends Player {

    private BetResultState betResultState;
    private final Money money;

    public Participant(final Name name, final Money money) {
        super(name);
        this.money = money;
        betResultState = null;
    }

    @Override
    public boolean canHit() {
        return cardArea.canMoreCard();
    }

    public void determineBetState(final BetResultState betResultState) {
        this.betResultState = betResultState;
    }

    public Money determineBetMoney() {
        return betResultState.calculateBetOutComeOf(money);
    }
}
