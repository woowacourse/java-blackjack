package domain;

public class Dealer extends Participant {
    private static final int CAN_RECEIVE_CARD_THRESHOLD = 17;

    public Dealer(Name name) {
        super(name);
    }

    public boolean isContinueGame() {
        if(cards.calculateScore() >= CAN_RECEIVE_CARD_THRESHOLD){
            return false;
        }
        return true;
    }
}
