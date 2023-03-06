package domain.player.participant;

import domain.player.Name;
import domain.player.Player;

public class Participant extends Player {

    public Participant(final Name name) {
        super(name);
    }

    @Override
    public boolean canHit() {
        return cardArea.canMoreCard();
    }
}
