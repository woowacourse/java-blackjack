package blakcjack.domain.participant;

import blakcjack.domain.score.Score;

public class Dealer extends Participant {
    private static final int DEALER_MAXIMUM_DRAWING_CRITERION = 17;

    public Dealer() {
        super("딜러");
    }

    public boolean needsAdditionalCard() {
        final Score score = cards.calculateScore();
        return score.isLowerThan(DEALER_MAXIMUM_DRAWING_CRITERION);
    }

    @Override
    public ParticipantType getType() {
        return ParticipantType.DEALER;
    }
}
