package domain.participant;

import domain.card.TrumpCard;
import domain.game.WinStatus;
import java.util.List;

public abstract class Participant {

    protected final ParticipantHand hand;
    private final ParticipantName name;

    protected Participant(ParticipantName name) {
        this.name = name;
        this.hand = new ParticipantHand();
    }

    public void addDraw(TrumpCard trumpCard) {
        hand.addCard(trumpCard);
    }

    public Score calculateCardSum() {
        return hand.calculateCardSum();
    }

    abstract boolean isDrawable();

    public boolean isBust() {
        Score score = hand.calculateCardSum();
        return hand.isBust(score);
    }

    public boolean isNameMatch(ParticipantName name) {
        return this.name.isMatch(name);
    }

    public List<TrumpCard> trumpCards() {
        return hand.getCards();
    }

    public ParticipantName name() {
        return name;
    }

    public boolean isDealer() {
        return false;
    }

    public WinStatus determineResult(Score other) {
        return hand.getWinStatusAgainst(other);
    }
}
