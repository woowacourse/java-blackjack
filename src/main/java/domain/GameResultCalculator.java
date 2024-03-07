package domain;

public class GameResultCalculator {
    /**
     * baseGamer의 otherGamer 에 대한 승부 결과 반환
     *
     * @param baseGamer  기준 게이머
     * @param otherGamer 상대 게이머
     * @return baseGamer의 otherGamer 에 대한 승부 결과
     */
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
