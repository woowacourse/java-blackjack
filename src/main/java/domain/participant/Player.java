package domain.participant;

import domain.game.GameResult;

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
        if (this.isBlackJack() && dealer.isBlackJack()) {
            return GameResult.DRAW;
        }
        if (this.isBlackJack()) {
            return GameResult.BLACKJACK;
        }
        if (this.isBust()) {
            return GameResult.LOSE;
        }
        if (dealer.isBust()) {
            return GameResult.WIN;
        }
        if (this.getScore() > dealer.getScore()) {
            return GameResult.WIN;
        }
        if (this.getScore() < dealer.getScore()) {
            return GameResult.LOSE;
        }
        return GameResult.DRAW;
    }
}
