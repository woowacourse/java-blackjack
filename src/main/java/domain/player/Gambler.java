package domain.player;

import domain.card.CardArea;

public class Gambler extends Participant {

    private HitState state = HitState.INIT;

    public Gambler(final Name name, final CardArea cardArea) {
        super(name, cardArea);
    }

    @Override
    public boolean canHit() {
        return cardArea.canMoreCard() && !state.isStay();
    }

    public boolean wantHit() {
        return state.isHit();
    }

    public void changeState(final HitState hitState) {
        state = hitState;
    }
}
