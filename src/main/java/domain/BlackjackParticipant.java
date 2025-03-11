package domain;

import java.util.List;

public abstract class BlackjackParticipant {

    protected final ParticipantHand hand;
    private final ParticipantName name;
    protected BlackjackParticipant(String name) {
        this.name = new ParticipantName(name);
        hand = new ParticipantHand();
    }

    public void addDraw(TrumpCard trumpCard) {
        hand.addCard(trumpCard);
    }

    public Score calculateCardSum() {
        return hand.calculateCardSum();
    }

    abstract boolean isDrawable();

    public boolean isBust() {
        return hand.isBust();
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
}
