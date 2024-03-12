package domain.participant;

import controller.dto.ParticipantHandStatus;
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

    @Override
    public ParticipantHandStatus createInitialHandStatus() {
        return createHandStatus();
    }
}
