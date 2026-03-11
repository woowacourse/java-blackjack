package domain;

public class Player extends Participant {
    private static final int CAN_RECEIVE_CARD_THRESHOLD = 21;

    public Player(Name name) {
        super(name);
    }

    public boolean isContinueGame() {
        return cards.canReceiveCard(CAN_RECEIVE_CARD_THRESHOLD);
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
