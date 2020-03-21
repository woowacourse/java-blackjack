package domain.game;

import domain.player.Player;
import domain.player.User;

import java.math.BigDecimal;

public class Result {
    private static final int ONE = 1;

    private int winCount;
    private int loseCount;
    private Money winningMoney;

    public Result() {
        this.winCount = 0;
        this.loseCount = 0;
        this.winningMoney = new Money("0");
    }

    public void addWinCount() {
        winCount++;
    }

    public void addLoseCount() {
        loseCount++;
    }

    public int getWinCount() {
        return winCount;
    }

    public int getLoseCount() {
        return loseCount;
    }

    public boolean isPlayCountMoreThanOne() {
        return winCount + loseCount > ONE;
    }

    public boolean hasWin() {
        return winCount >= ONE;
    }

    public void calculateWinningMoney(Player mainPlayer, Player opponentPlayer, BettingLog userBettingLog) {
        BigDecimal bettingMoney = userBettingLog.getMoney();

        if (isBlackJackWin(mainPlayer, opponentPlayer)) {
            winningMoney = winningMoney.addMoney(bettingMoney.multiply(new BigDecimal("1.5")));
            return;
        }
        if (isBlackJackDraw(mainPlayer, opponentPlayer)
                || isNormalDraw(mainPlayer, opponentPlayer)) {
            return;
        }
        if (isNormalWin(mainPlayer, opponentPlayer)) {
            winningMoney = winningMoney.addMoney(bettingMoney);
            return;
        }
        if (isBlackJackLose(opponentPlayer)) {
            winningMoney = winningMoney.subtractMoney(bettingMoney.multiply(new BigDecimal("1.5")));
            return;
        }
        winningMoney = winningMoney.subtractMoney(bettingMoney);
    }

    private boolean isBlackJackWin(Player mainPlayer, Player opponentPlayer) {
        return mainPlayer instanceof User
                && mainPlayer.isWin(opponentPlayer)
                && mainPlayer.isBlackJack()
                && !opponentPlayer.isBlackJack();
    }

    private boolean isBlackJackDraw(Player mainPlayer, Player opponentPlayer) {
        return mainPlayer.isWin(opponentPlayer)
                && mainPlayer.isBlackJack()
                && opponentPlayer.isBlackJack();
    }

    private boolean isNormalDraw(Player mainPlayer, Player opponentPlayer) {
        return mainPlayer.isDraw(opponentPlayer);
    }

    private boolean isNormalWin(Player mainPlayer, Player opponentPlayer) {
        return mainPlayer.isWin(opponentPlayer);
    }

    private boolean isBlackJackLose(Player opponentPlayer) {
        return opponentPlayer instanceof User
                && opponentPlayer.isBlackJack();
    }

    public BigDecimal getWinningMoney() {
        return winningMoney.getMoney();
    }
}
