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

    public void pickCard(final Deck deck) {
        participant.pickCard(deck);
    }

    public void duel(final Player player) {
        player.duel(participant);
    }

    public Participant getParticipant() {
        return participant;
    }
}
