package domain;

public class Dealer {
    private final Participant participant;

    public Dealer() {
        this.participant = new Participant();
    }

    public Dealer(final CardHand hand) {
        this.participant = new Participant(hand);
    }

    public boolean isPickCard() {
        return participant.calculateAllScore() <= 16;
    }

    public void pickCardOnFirstHandOut(final Deck deck) {
        participant.pickCardOnFirstHandOut(deck);
    }

    public void pickCard(final Deck deck) {
        participant.pickCard(deck);
    }

    public Participant getParticipant() {
        return participant;
    }
}
