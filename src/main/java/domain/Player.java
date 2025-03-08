package domain;

public class Player {
    private static final int MAX_SCORE = 21;
    
    private final String name;
    private final Participant participant;

    private boolean isWinDuel = false;

    public Player(final String name) {
        this.participant = new Participant();
        this.name = name;
    }

    public Player(final String name, final Participant participant) {
        this.participant = participant;
        this.name = name;
    }

    public boolean isPickCard() {
        return participant.calculateScore() <= MAX_SCORE;
    }

    public void pickCardOnFirstHandOut(final Deck deck) {
        participant.pickCardOnFirstHandOut(deck);
    }

    public void pickCard(final Deck deck) {
        participant.pickCard(deck);
    }

    public void duel(final Participant other) {
        final int score = participant.calculateScore();
        final int otherScore = other.calculateScore();
        if (score > MAX_SCORE) {
            return;
        }
        if (otherScore > MAX_SCORE) {
            isWinDuel = true;
            return;
        }
        isWinDuel = score > otherScore;
    }

    public boolean isWinDuel() {
        return isWinDuel;
    }

    public String getName() {
        return name;
    }

    public Participant getParticipant() {
        return participant;
    }
}
