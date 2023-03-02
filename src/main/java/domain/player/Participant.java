package domain.player;

import domain.area.CardArea;

public class Participant extends Player {

    private final States states = States.init();

    public Participant(final Name name, final CardArea cardArea) {
        super(name, cardArea);
    }

    @Override
    public boolean canHit() {
        return cardArea.canMoreCard() && !states.isStay();
    }

    public boolean wantHit() {
        return states.isHit();
    }

    public void changeState(final HitState hitState) {
        states.setState(hitState);
    }
}
