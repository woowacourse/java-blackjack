package domain;

public class Dealer extends Participant {
    private static final int CAN_RECEIVE_CARD_THRESHOLD = 17;

    public Dealer(Name name) {
        super(name);
    }

    public boolean isContinueGame() {
        return cards.canReceiveCard(CAN_RECEIVE_CARD_THRESHOLD);
    }
}
