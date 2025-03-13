package domain.participant;

import domain.blackjackgame.BlackjackWinner;
import domain.blackjackgame.PlayerGameResult;
import exception.BlackJackException;

public class BlackjackBet {

    private static final double BLACKJACK_WIN_MULTIPLE = 1.5;
    private static final double WINNING_MULTIPLE = 2.0;
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

    public double calculateWinningMoney(BlackjackCardSum blackjackCardSum, BlackjackCardSum otherCardSum) {
        int cardSum = blackjackCardSum.calculateCardSum();
        PlayerGameResult playerGameResult = BlackjackWinner.calculatePlayerWinStatus(otherCardSum.calculateCardSum(),
                cardSum);
        if (playerGameResult == PlayerGameResult.DRAW) {
            return money;
        }
        if (blackjackCardSum.isBust()) {
            return 0;
        }
        if (blackjackCardSum.isBlackjack()) {
            return money + (money * BLACKJACK_WIN_MULTIPLE);
        }
        return calculateWinningMoney(playerGameResult);
    }

    private double calculateWinningMoney(PlayerGameResult playerGameResult) {
        if (playerGameResult == PlayerGameResult.WIN) {
            return money * WINNING_MULTIPLE;
        }
        if (playerGameResult == PlayerGameResult.DRAW) {
            return money;
        }
        return 0;
    }

    public double betMoney() {
        return money;
    }
}
