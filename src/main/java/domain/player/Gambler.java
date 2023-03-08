package domain.player;

import domain.card.CardArea;

public class Gambler extends Participant {

    private final BettingMoney bettingMoney;

    private HitState state = HitState.INIT;

    public Gambler(final Name name, final CardArea cardArea, final BettingMoney bettingMoney) {
        super(name, cardArea);
        this.bettingMoney = bettingMoney;
    }

    public BettingMoney battingMoney() {
        return bettingMoney;
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

    public boolean isBlackJack() {
        return cardArea.isBlackJack();
    }
}
