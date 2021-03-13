package blakcjack.domain.participant;

import blakcjack.domain.score.Score;

public class Dealer extends Participant {
    private static final int DEALER_MAXIMUM_DRAWING_CRITERION = 17;
    private static final int INITIAL_MONEY = 0;

    public Dealer() {
        super("딜러", INITIAL_MONEY);
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
