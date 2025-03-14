package domain.participant;

import domain.card.TrumpCard;
import domain.game.WinStatus;
import java.util.List;

public class Player {
    private final Participant participant;

    public Player(ParticipantName name) {
        participant = new Participant(name);
    }

    boolean isDrawable() {
        return !participant.isBust();
    }

    public ParticipantName name(){
        return participant.name();
    }

    public Score calculateCardSum(){
        return participant.calculateCardSum();
    }

    public List<TrumpCard> cards(){
        return participant.trumpCards();
    }

    public boolean isBust(){
        return participant.isBust();
    }

    public void addCard(TrumpCard trumpCard){
        participant.addDraw(trumpCard);
    }

    public WinStatus determineResult(Score other){
        return participant.determineResult(other);
    }
}
