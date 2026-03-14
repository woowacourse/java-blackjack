package domain.game;

import domain.betting.BettingAmount;
import domain.player.Gambler;
import domain.player.Participant;

public enum GameResult {

    BLACK_JACK(1.5),
    WIN(1.0),
    LOSE(-1.0),
    DRAW(0.0);

    private final double benefitRatio;

    GameResult(double gameResult) {
        this.benefitRatio = gameResult;
    }

    public static GameResult determine(Participant dealer, Participant gambler) {
        if(gambler.isBust()) {
            return LOSE;
        }

        if(dealer.isBust()) {
            return WIN;
        }
        if (dealer.isBlackJack() && gambler.isBlackJack()) {
            return DRAW;
        }
        if (gambler.isBlackJack()) {
            return BLACK_JACK;
        }
        if (dealer.isBlackJack()) {
            return LOSE;
        }
        if (dealer.getTotalScore() > gambler.getTotalScore()) {
            return LOSE;
        }
        if (dealer.getTotalScore() < gambler.getTotalScore()) {
            return WIN;
        }
        return DRAW;
    }
    public Profit calculateProfit(BettingAmount bettingAmount) {
        if(this == BLACK_JACK) {
            return new Profit((bettingAmount.getBettingAmount() * 3)/2);
        }
        if(this == WIN) {
            return new Profit(bettingAmount.getBettingAmount());
        }
        if(this == DRAW) {
            return new Profit(0);
        }
        return new Profit(-bettingAmount.getBettingAmount());
    }
}
