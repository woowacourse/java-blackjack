package domain.participant;

import domain.area.CardArea;

import java.util.Objects;

public class Participant extends Player {

    private State state;

    public Participant(final Name name, final CardArea cardArea) {
        super(name, cardArea);
    }

    @Override
    public boolean canHit() {
        return cardArea.canMoreCard() &&
                (Objects.isNull(state) || wantHit());
    }

    public boolean wantHit() {
        return state.isHit();
    }

    public void changeState(final State state) {
        this.state = state;
    }
}
