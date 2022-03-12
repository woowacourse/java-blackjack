package domain.participant;

import domain.CardScoreThreshold;
import domain.card.CardDeck;

public final class Player extends Participant {

    public Player(final String name) {
        super(CardScoreThreshold.PLAYER_THRESHOLD, name);
    }

    public String getName() {
        return name;
    }
//    @Override
//    public List<FinalGameResult> calculateFinalGameResult(Participant other) {
//        return null;
//    }
}
