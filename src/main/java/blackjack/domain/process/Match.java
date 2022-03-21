package blackjack.domain.process;

import blackjack.domain.gamer.Gamer;

public enum Match {
    BLACKJACK_WIN(1.5),
    NOT_BLACKJACK_WIN(1.0),
    DRAW(0.0),
    LOSE(-1.0);

    private final double ratio;

    Match(double ratio) {
        this.ratio = ratio;
    }

    public static Match of(Gamer thisGamer, Gamer otherGamer) {
        if (thisGamer.isDraw(otherGamer)) {
            return DRAW;
        }
        if (thisGamer.isLose(otherGamer)) {
            return LOSE;
        }
        if (thisGamer.isBlackJack()) {
            return BLACKJACK_WIN;
        }
        return NOT_BLACKJACK_WIN;
    }

    public double getRatio() {
        return ratio;
    }
}
