package domain;

public class Player extends Participant {
    private static final int CAN_RECEIVE_CARD_THRESHOLD = 21;

    public Player(Name name) {
        super(name);
    }

    public boolean isContinueGame() {
        if (participantCards.calculateScore() >= CAN_RECEIVE_CARD_THRESHOLD) {
            return false;
        }
        return true;
    }

    public GameResult judgeResult(Dealer dealer) {
        if (this.isBust()) { // TODO: 배팅 금액을 모두 잃는다.
            return GameResult.LOSE;
        }
        if (dealer.isBust()) { // TODO: 배팅 금액을 받는다.
            return GameResult.WIN;
        }
        if (this.getScore() > dealer.getScore()) {
            return GameResult.WIN; // TODO: 배팅 금액을 받는다.
        }
        if (this.getScore() < dealer.getScore()) {
            return GameResult.LOSE; // TODO: 배팅 금액을 모두 잃는다.
        }
        return GameResult.DRAW; // TODO: 배팅 금액을 돌려 받는다.
    }
}
