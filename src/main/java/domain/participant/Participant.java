package domain.participant;

import domain.card.TrumpCard;
import domain.participant.state.HandState;
import java.util.List;

public class Participant {

    private final ParticipantName name;
    private HandState hand;

    public Participant(ParticipantName name, List<TrumpCard> initCards) {
        this.name = name;
        this.hand = HandState.start(initCards);
    }

    public void addDraw(TrumpCard trumpCard) {
        this.hand = hand.addCard(trumpCard);
    }

    public Score calculateCardSum() {
        return hand.calculateCardSum();
    }

    public Score calculateCardSum(Score aceCalcStandard) {
        return hand.calculateCardSum(aceCalcStandard);
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public List<TrumpCard> trumpCards() {
        return hand.getCards();
    }

    public ParticipantName name() {
        return name;
    }

    public void stay() {
        this.hand = hand.stay();
    }

    public HandState handState() {
        return hand;
    }

    public boolean isFinished() {
        return hand.isFinished();
    }
}
