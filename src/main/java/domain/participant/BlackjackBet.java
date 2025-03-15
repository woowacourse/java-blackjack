package domain.participant;

import domain.blackjackgame.BlackjackWinner;
import domain.blackjackgame.PlayerGameResult;
import exception.BlackJackException;

public class BlackjackBet {

    private static final double BLACKJACK_WIN_MULTIPLE = 1.5;
    private static final double WINNING_MULTIPLE = 1.0;
    private static final String INVALID_BET_MONEY = "베팅 금액은 1원부터입니다.";

    private final int money;

    public BlackjackBet(int money) {
        validateMoney(money);
        this.money = money;
    }

    private void validateMoney(int money) {
        if (money < 0) {
            throw new BlackJackException(INVALID_BET_MONEY);
        }
    }

    public double calculateEarnMoney(BlackjackHands calculateParticipantCardSum,
                                     BlackjackHands againstParticipantCardSum) {
        int cardSum = calculateParticipantCardSum.calculateCardSum();
        PlayerGameResult playerGameResult = BlackjackWinner.calculatePlayerWinStatus(
                againstParticipantCardSum.calculateCardSum(),
                cardSum);
        if (playerGameResult.isDraw()) {
            return money;
        }
        if (calculateParticipantCardSum.isBust()) {
            return 0;
        }
        if (calculateParticipantCardSum.isBlackjack()) {
            return money + (money * BLACKJACK_WIN_MULTIPLE);
        }
        return calculateEarnMoney(playerGameResult);
    }

    private double calculateEarnMoney(PlayerGameResult playerGameResult) {
        if (playerGameResult.isWin()) {
            return money + money * WINNING_MULTIPLE;
        }
        return 0;
    }

    public double betMoney() {
        return money;
    }
}
