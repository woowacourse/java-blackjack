package domain.game;

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


}
