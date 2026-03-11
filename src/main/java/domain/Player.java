package domain;

public class Player extends Participant {
    private static final int BURST_THRESHOLD = 21;

    public Player(Name name) {
        super(name);
    }

    public boolean isContinueGame() {
        return cards.canReceiveCard(BURST_THRESHOLD);
    }

    public boolean isWin(Dealer dealer) {
        if (this.isBurst()) {
            return false;
        }
        if (dealer.isBurst()) {
            return true;
        }
        return this.getScore() >= dealer.getScore();
    }
}
