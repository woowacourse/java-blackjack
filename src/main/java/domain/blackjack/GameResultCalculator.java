package domain.blackjack;

class GameResultCalculator {

    static GameResult calculate(Gamer baseGamer, Gamer otherGamer) {
        if (baseGamer.isBust() && otherGamer.isBust()) {
            return GameResult.TIE;
        }
        if (baseGamer.isBust()) {
            return GameResult.LOSE;
        }
        if (otherGamer.isBust()) {
            return GameResult.WIN;
        }
        return getGameResultWhenNobodyDead(baseGamer, otherGamer);
    }

    private static GameResult getGameResultWhenNobodyDead(Gamer baseGamer, Gamer otherGamer) {
        SummationCardPoint baseSummationCardPoint = baseGamer.calculateSummationCardPoint();
        SummationCardPoint otherSummationCardPoint = otherGamer.calculateSummationCardPoint();
        if (baseSummationCardPoint.isBiggerThan(otherSummationCardPoint)) {
            return GameResult.WIN;
        }
        if (baseSummationCardPoint.equals(otherSummationCardPoint)) {
            return GameResult.TIE;
        }
        return GameResult.LOSE;
    }
}
