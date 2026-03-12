package domain;

public class Player extends Participant {
    private static final int CAN_RECEIVE_CARD_THRESHOLD = 21;

    public Player(Name name) {
        super(name);
    }

    public boolean isContinueGame() {
        if (cards.calculateScore() >= CAN_RECEIVE_CARD_THRESHOLD) {
            return false;
        }
        return true;
    }

    public boolean isWin(Dealer dealer) {
        if (this.isBust()) {
            return false;
        }
        if (dealer.isBust()) {
            return true;
        }
        return this.getScore() >= dealer.getScore();
    }
}
