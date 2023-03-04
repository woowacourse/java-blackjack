package domain.player.participant;

import domain.area.CardArea;
import domain.player.Name;
import domain.player.Player;

public class Participant extends Player {

    public Participant(final Name name, final CardArea cardArea) {
        super(name, cardArea);
    }

    @Override
    public boolean canHit() {
        return cardArea.canMoreCard();
    }
}
