package blakcjack.domain.participant;

import blakcjack.domain.card.Card;
import blakcjack.domain.score.Score;

import java.util.List;

public class Dealer extends Participant {
    private static final int DEALER_MAXIMUM_DRAWING_CRITERION = 17;

    public Dealer() {
        super("딜러");
    }

    public List<Card> showFirstCard() {
        return cards.toListOnlyFirstCard();
    }

    public boolean needsAdditionalCard() {
        final Score score = cards.calculateScore();
        return score.isLowerThan(DEALER_MAXIMUM_DRAWING_CRITERION);
    }

    @Override
    public boolean supports(final ParticipantType participantType) {
        return ParticipantType.DEALER.equals(participantType);
    }
}
