package domain.player;

import domain.card.CardArea;
import domain.game.BattingMoney;

public class Gambler extends Participant {

    private HitState state = HitState.INIT;
    private final BattingMoney battingMoney;

    public Gambler(final Name name, final CardArea cardArea, final BattingMoney battingMoney) {
        super(name, cardArea);
        this.battingMoney = battingMoney;
    }

    public BattingMoney battingMoney() {
        return battingMoney;
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
