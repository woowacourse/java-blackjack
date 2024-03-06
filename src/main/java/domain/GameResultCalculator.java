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
        if (baseGamer.getSummationCardPoint().isBiggerThan(otherGamer.getSummationCardPoint())) {
            return GameResult.WIN;
        }
        if (baseGamer.getSummationCardPoint().equals(otherGamer.getSummationCardPoint())) {
            return GameResult.TIE;
        }
        return GameResult.LOSE;
    }
}
