package domain;

public class Player extends Participant {
    private static final int BURST_THRESHOLD = 21;

    public Player(Name name) {
        super(name);
    }

    public boolean isContinueGame() {
        return cards.isBurst(BURST_THRESHOLD);
    }
}
