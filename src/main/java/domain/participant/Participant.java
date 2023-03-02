package domain.participant;

import domain.area.CardArea;

public class Participant extends Player {

    private State state;

    public Participant(final Name name, final CardArea cardArea) {
        super(name, cardArea);
    }

    @Override
    public boolean canHit() {
        return cardArea.canMoreCard() && state != State.STAY;
    }

    public boolean wantHit() {
        return state == State.HIT;
    }

    public void changeState(final State state) {
        this.state = state;
    }
}
