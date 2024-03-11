package blackjackgame.domain.blackjack;

public class GameResultCalculator {

    private GameResultCalculator() {
    }

    public static GameResult calculate(Gamer baseGamer, Gamer otherGamer) {
        if (baseGamer.isDead() && otherGamer.isDead()) {
            return GameResult.TIE;
        }
        if (baseGamer.isDead()) {
            return GameResult.LOSE;
        }
        if (otherGamer.isDead()) {
            return GameResult.WIN;
        }
        return getGameResultWhenNobodyDead(baseGamer, otherGamer);
    }

    private static GameResult getGameResultWhenNobodyDead(Gamer baseGamer, Gamer otherGamer) {
        SummationCardPoint baseGamerSummationCardPoint = baseGamer.getSummationCardPoint();
        SummationCardPoint otherGamerSummationCardPoint = otherGamer.getSummationCardPoint();

        if (baseGamerSummationCardPoint.isBiggerThan(otherGamerSummationCardPoint)) {
            return GameResult.WIN;
        }
        if (baseGamerSummationCardPoint.equals(otherGamerSummationCardPoint)) {
            return GameResult.TIE;
        }
        return GameResult.LOSE;
    }
}
