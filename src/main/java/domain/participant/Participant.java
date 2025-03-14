package domain.participant;

import domain.card.TrumpCard;
import domain.game.WinStatus;
import domain.participant.state.HandState;
import java.util.List;

public class Participant {

    private final HandState hand;
    private final ParticipantName name;

    public Participant(ParticipantName name, TrumpCard... initCards) {
        this.name = name;
        this.hand = HandState.start(initCards);
    }

    public void addDraw(TrumpCard trumpCard) {
        hand.addCard(trumpCard);
    }

    public Score calculateCardSum() {
        return hand.calculateCardSum();
    }

    public Score calculateCardSum(Score aceCalcStandard){
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

    public WinStatus determineResult(Score other) {
        return hand.getWinStatusAgainst(other);
    }
}
