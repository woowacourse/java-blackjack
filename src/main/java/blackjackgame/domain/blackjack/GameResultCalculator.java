package blackjackgame.domain.blackjack;

public class GameResultCalculator {

    private GameResultCalculator() {
    }

    public static GameResult calculate(CardHolder baseCardHolderGamer, CardHolder otherCardHolderGamer) {
        if (baseCardHolderGamer.isDead() && otherCardHolderGamer.isDead()) {
            return GameResult.TIE;
        }
        if (baseCardHolderGamer.isDead()) {
            return GameResult.LOSE;
        }
        if (otherCardHolderGamer.isDead()) {
            return GameResult.WIN;
        }
        return getGameResultWhenNobodyDead(baseCardHolderGamer, otherCardHolderGamer);
    }

    private static GameResult getGameResultWhenNobodyDead(CardHolder baseCardHolderGamer, CardHolder otherCardHolderGamer) {
        SummationCardPoint baseGamerSummationCardPoint = baseCardHolderGamer.getSummationCardPoint();
        SummationCardPoint otherGamerSummationCardPoint = otherCardHolderGamer.getSummationCardPoint();

        if (baseGamerSummationCardPoint.isBiggerThan(otherGamerSummationCardPoint)) {
            return GameResult.WIN;
        }
        if (baseGamerSummationCardPoint.equals(otherGamerSummationCardPoint)) {
            return GameResult.TIE;
        }
        return GameResult.LOSE;
    }
}
