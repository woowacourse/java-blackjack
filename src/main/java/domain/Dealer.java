package domain;

public class Dealer extends Participant {
    private static final int BURST_THRESHOLD = 17;

    public Dealer(Name name) {
        super(name);
    }

    public boolean isContinueGame() {
        return cards.isBurst(BURST_THRESHOLD);
    }
}
