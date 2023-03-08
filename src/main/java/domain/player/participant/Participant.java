package domain.player.participant;

import domain.player.Name;
import domain.player.Player;
import domain.player.participant.betresult.BetResultState;

public class Participant extends Player {

    private BetResultState betResultState;

    public Participant(final Name name) {
        super(name);
        betResultState = null;
    }

    @Override
    public boolean canHit() {
        return cardArea.canMoreCard();
    }

    public void determineBetState(final BetResultState betResultState) {
        this.betResultState = betResultState;
    }
}
