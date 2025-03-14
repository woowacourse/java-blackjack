package domain.participant;

import domain.card.TrumpCard;
import java.util.List;

public class Dealer {

    private static final String DEALER_NAME = "딜러";
    private static final Score DEALER_STOP_HIT_STANDARD_SCORE = Score.from(16);
    private final Participant participant;

    public Dealer() {
        ParticipantName dealerName = new ParticipantName(DEALER_NAME);
        participant = new Participant(dealerName);
    }

    public boolean isDrawable() {
        Score totalScore = participant.calculateCardSum(DEALER_STOP_HIT_STANDARD_SCORE);
        return totalScore.isLessThanEqual(DEALER_STOP_HIT_STANDARD_SCORE);
    }

    public List<TrumpCard> cards(){
        return participant.trumpCards();
    }

    public ParticipantName name(){
        return participant.name();
    }

    public Score calculateCardSum(){
        return participant.calculateCardSum();
    }

    public void addCard(TrumpCard trumpCard){
        participant.addDraw(trumpCard);
    }


}
