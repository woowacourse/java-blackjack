package domain.blackjack;

public class GameResultCalculator {
    /**
     * baseGamer의 otherGamer 에 대한 승부 결과 반환
     *
     * @param baseBlackJackGameMachine  기준 게이머
     * @param otherBlackJackGameMachine 상대 게이머
     * @return baseGamer의 otherGamer 에 대한 승부 결과
     */
    public static GameResult calculate(BlackJackGameMachine baseBlackJackGameMachine,
                                       BlackJackGameMachine otherBlackJackGameMachine) {
        if (baseBlackJackGameMachine.isBust() && otherBlackJackGameMachine.isBust()) {
            return GameResult.TIE;
        }
        if (baseBlackJackGameMachine.isBust()) {
            return GameResult.LOSE;
        }
        if (otherBlackJackGameMachine.isBust()) {
            return GameResult.WIN;
        }
        return getGameResultWhenNobodyDead(baseBlackJackGameMachine, otherBlackJackGameMachine);
    }

    private static GameResult getGameResultWhenNobodyDead(BlackJackGameMachine baseBlackJackGameMachine,
                                                          BlackJackGameMachine otherBlackJackGameMachine) {
        SummationCardPoint baseGamerSummationCardPoint = baseBlackJackGameMachine.calculateSummationCardPoint();
        SummationCardPoint otherGamerSummationCardPoint = otherBlackJackGameMachine.calculateSummationCardPoint();

        if (baseGamerSummationCardPoint.isBiggerThan(otherGamerSummationCardPoint)) {
            return GameResult.WIN;
        }
        if (baseGamerSummationCardPoint.equals(otherGamerSummationCardPoint)) {
            return GameResult.TIE;
        }
        return GameResult.LOSE;
    }
}
