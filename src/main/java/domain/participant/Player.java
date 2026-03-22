package domain.participant;

import domain.betting.BettingAmount;
import domain.betting.Revenue;
import domain.game.GameResult;

public class Player extends Participant {
    private static final int CAN_RECEIVE_CARD_THRESHOLD = 21;

    private final BettingAmount bettingAmount;

    public Player(Name name, BettingAmount bettingAmount) {
        super(name);
        this.bettingAmount = bettingAmount;
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

    public Revenue calculateRevenueAgainst(Dealer dealer) {
        GameResult result = judgeResult(dealer);
        return new Revenue(bettingAmount.getMoney().multiply(result.getProfitRate()));
    }
}
