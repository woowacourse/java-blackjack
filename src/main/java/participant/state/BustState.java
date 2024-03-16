package participant.state;

import participant.Participant;

public class BustState implements State {

    private static final int MAX_BLACK_JACK_SCORE = 21;

    @Override
    public boolean isCorespondentState(Participant participant) {
        return participant.getCardScore() > MAX_BLACK_JACK_SCORE;
    }

    @Override
    public boolean canHit() {
        return false;
    }

    @Override
    public boolean isBust() {
        return true;
    }

    @Override
    public boolean isBlackJack() {
        return false;
    }
}
