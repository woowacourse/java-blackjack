package participant.state;

import participant.Participant;

public class BlackJackState implements State {

    private static final int MAX_BLACK_JACK_SCORE = 21;
    private static final int INIT_CARD_SETTING_COUNT = 2;

    @Override
    public boolean isCorespondentState(Participant participant) {
        return participant.getCardScore() == MAX_BLACK_JACK_SCORE
                && participant.getCards().countCard() == INIT_CARD_SETTING_COUNT;
    }

    @Override
    public boolean canHit() {
        return false;
    }

    @Override
    public boolean isBust() {
        return false;
    }

    @Override
    public boolean isBlackJack() {
        return true;
    }
}
