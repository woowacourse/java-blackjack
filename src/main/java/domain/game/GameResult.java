package domain.game;

import domain.player.Participant;

public enum GameResult {

    BLACK_JACK(1.5), WIN(1.0), LOSE(-1.0), DRAW(0.0);

    private final double benefitRatio;

    GameResult(double gameResult) {
        this.benefitRatio = gameResult;
    }

    public static GameResult determine(Participant dealer, Participant gambler) {
        if (gambler.isBust()) {
            return LOSE;
        }
        if (gambler.isBlackJack() && dealer.isBlackJack()) {
            return DRAW;
        }
        if (gambler.isBlackJack()) {
            return BLACK_JACK;
        }
        if (dealer.isBust()) {
            return WIN;
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

    public double getBenefitRatio() {
        return benefitRatio;
    }
}
