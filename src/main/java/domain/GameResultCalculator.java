package domain;

public class GameResultCalculator {
    /**
     * baseGamer의 otherGamer 에 대한 승부 결과 반환
     *
     * @param baseGamer  기준 게이머
     * @param otherGamer 상대 게이머
     * @return baseGamer의 otherGamer 에 대한 승부 결과
     */
    // TODO: 메서드 분리 필요
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

        // TODO: 딜러가 Ace를 가지고 있는 경우 고려해야 함

        SummationCardPoint baseGamerSummationCardPoint = fix(baseGamer);
        SummationCardPoint otherGamerSummationCardPoint = fix(otherGamer);

        if (baseGamerSummationCardPoint.isBiggerThan(otherGamerSummationCardPoint)) {
            return GameResult.WIN;
        }
        if (baseGamerSummationCardPoint.equals(otherGamerSummationCardPoint)) {
            return GameResult.TIE;
        }
        return GameResult.LOSE;
    }

    // TODO: 메서드명 변경 필요
    private static SummationCardPoint fix(Gamer gamer) {
        int rawPoint = gamer.getSummationCardPoint().summationCardPoint();

        if (gamer.hasAceInHoldingCards()) {
            rawPoint = fixPoint(rawPoint);
            return new SummationCardPoint(rawPoint);
        }
        return gamer.getSummationCardPoint();
    }

    // TODO: 메서드명, 변수명 변경 필요
    private static int fixPoint(int rawPoint) {
        int fixPoint = rawPoint + 10;
        if (fixPoint <= 21) {
            rawPoint = fixPoint;
        }
        return rawPoint;
    }
}
