package domain.participant;

import domain.card.TrumpCard;
import domain.participant.state.HandState;
import java.util.List;

public class Dealer {

    private static final String DEALER_NAME = "딜러";
    private static final Score DEALER_STOP_HIT_STANDARD_SCORE = Score.from(16);
    private final Participant participant;

    public Dealer(TrumpCard... initCards) {
        ParticipantName dealerName = new ParticipantName(DEALER_NAME);
        participant = new Participant(dealerName, initCards);
    }

    public boolean isDrawable() {
        if (participant.handState().isFinished()) {
            return false;
        }
        Score totalScore = participant.calculateCardSum(DEALER_STOP_HIT_STANDARD_SCORE);
        return totalScore.isLessThanEqual(DEALER_STOP_HIT_STANDARD_SCORE);
    }

    public List<TrumpCard> cards() {
        return participant.trumpCards();
    }

    public ParticipantName name() {
        return participant.name();
    }

    public void addCard(TrumpCard trumpCard) {
        participant.addDraw(trumpCard);
    }

    public Score calculateSum() {
        return handState().calculateCardSum();
    }

    public HandState handState() {
        return participant.handState();
    }
}
