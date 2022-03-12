package domain.participant;

import domain.CanAddCardThreshold;

public final class Player extends Participant {

    public Player(final String name) {
        super(CanAddCardThreshold.PLAYER_THRESHOLD, name);
    }

//    @Override
//    public List<FinalGameResult> calculateFinalGameResult(Participant other) {
//        return null;
//    }
}
