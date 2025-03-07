package domain;

public class Dealer {
    private static final int DEALER_MAX_SCORE_FOR_PICK = 16;
    private final Participant participant;
    private int winCount = 0;
    private int loseCount = 0;

    public Dealer() {
        this.participant = new Participant();
    }

    public Dealer(final CardHand hand) {
        this.participant = new Participant(hand);
    }

    public boolean isPickCard() {
        return participant.calculateAllScore() <= DEALER_MAX_SCORE_FOR_PICK;
    }

    public void pickCardOnFirstHandOut(final Deck deck) {
        participant.pickCardOnFirstHandOut(deck);
    }

    public void pickCard(final Deck deck) {
        participant.pickCard(deck);
    }

    public void startDuel(final Player player) {
        player.duel(participant);
        final boolean isWinPlayerDuelResult = player.getIsWinDuel();
        if (isWinPlayerDuelResult) {
            loseCount++;
            return;
        }
        winCount++;
    }

    public Participant getParticipant() {
        return participant;
    }

    public int getWinCount() {
        return winCount;
    }

    public int getLoseCount() {
        return loseCount;
    }
}
