package participant.state;

import participant.Participant;

public class CanHitState implements State {

    private static final int INIT_CARD_SETTING_COUNT = 2;

    @Override
    public boolean isCorespondentState(Participant participant) {
        return participant.getCards().countCard() >= INIT_CARD_SETTING_COUNT;
    }

    @Override
    public boolean canHit() {
        return true;
    }

    @Override
    public boolean isBust() {
        return false;
    }

    @Override
    public boolean isBlackJack() {
        return false;
    }
}
