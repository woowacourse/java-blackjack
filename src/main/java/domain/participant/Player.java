package domain.participant;

import domain.constants.CardCommand;
import domain.game.DecisionToContinue;

public class Player extends Participant {
    public Player(final String name) {
        super(name);
    }

    @Override
    public boolean canPickCard(final DecisionToContinue decision) {
        return isNotBusted() && CardCommand.HIT.equals(decision.get());
    }
}
