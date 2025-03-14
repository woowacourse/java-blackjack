package domain.participant;

import domain.card.TrumpCard;
import domain.game.WinStatus;
import java.util.List;

public class Participant {

    private final ParticipantHand hand;
    private final ParticipantName name;

    public Participant(ParticipantName name) {
        this.name = name;
        this.hand = new ParticipantHand();
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
        Score score = hand.calculateCardSum();
        return hand.isBust(score);
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
