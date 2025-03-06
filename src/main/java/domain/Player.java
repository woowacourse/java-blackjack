package domain;

public class Player {
    private final String name;
    private final Participant participant;
    private boolean isWinDuel;

    public Player(String name) {
        this.participant = new Participant();
        this.name = name;
        isWinDuel = false;
    }

    public Player(final Participant participant) {
        this.participant = participant;
        this.name = "";
        isWinDuel = false;
    }

    public boolean isPickCard() {
        return participant.calculateAllScore() <= 21;
    }

    public void pickCardOnFirstHandOut(final Deck deck) {
        participant.pickCardOnFirstHandOut(deck);
    }

    public void pickCard(final Deck deck) {
        participant.pickCard(deck);
    }

    public void duel(final Participant other) {
        final int score = participant.calculateAllScore();
        if (score > 21) {
            return;
        }
        isWinDuel = score > other.calculateAllScore();
    }

    public boolean getIsWinDuel() {
        return isWinDuel;
    }

    public String getName() {
        return name;
    }

    public Participant getParticipant() {
        return participant;
    }
}
