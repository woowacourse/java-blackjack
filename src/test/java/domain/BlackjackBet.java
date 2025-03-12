package domain;

import domain.blackjackgame.BlackjackWinner;
import domain.blackjackgame.PlayerGameResult;
import domain.participant.BlackjackCardSum;
import exception.BlackJackException;

public class BlackjackBet {

    private static final double BLACKJACK_WIN_MULTIPLE = 1.5;
    private static final double WINNING_MULTIPLE = 2.0;
    private static final String INVALID_BET_MOENY = "베팅 금액은 1원부터입니다.";

    private final int money;


    public BlackjackBet(int money) {
        validateMoney(money);
        this.money = money;
    }

    private void validateMoney(int money) {
        if (money <= 0) {
            throw new BlackJackException(INVALID_BET_MOENY);
        }
    }

    public double calculateWinningMoney(BlackjackCardSum blackjackCardSum, BlackjackCardSum otherCardSum) {
        int cardSum = blackjackCardSum.calculateCardSum();
        if (blackjackCardSum.isBust()) {
            return 0;
        }
        if (blackjackCardSum.isBlackjack()) {
            return money + (money * BLACKJACK_WIN_MULTIPLE);
        }
        PlayerGameResult playerGameResult = BlackjackWinner.calculatePlayerWinStatus(otherCardSum.calculateCardSum(),
                cardSum);
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
}
