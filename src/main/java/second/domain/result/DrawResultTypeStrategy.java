package second.domain.result;

import second.domain.gamer.Gamer;

public class DrawResultTypeStrategy implements ResultTypeStrategy {
    @Override
    public boolean judge(Gamer gamer, Gamer counterGamer) {
        return isBothBlackJack(gamer, counterGamer) || isSameScore(gamer, counterGamer);
    }

    private boolean isSameScore(Gamer gamer, Gamer counterGamer) {
        return !gamer.isBust() && !counterGamer.isBust() && gamer.isSameScoreAs(counterGamer);
    }

    private boolean isBothBlackJack(Gamer gamer, Gamer counterGamer) {
        return gamer.isBlackJack() && counterGamer.isBlackJack();
    }
}
