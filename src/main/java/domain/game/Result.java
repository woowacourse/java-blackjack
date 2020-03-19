package domain.game;

import domain.betting.BettingLog;
import domain.player.Player;
import domain.player.User;

import java.math.BigDecimal;

public class Result {
    private static final int ONE = 1;
    private final String name;
    private int winCount;
    private int loseCount;
    private Money winningMoney;

    public Result(final String name) {
        this.name = name;
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

    public String getName() {
        return name;
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
            winningMoney.addMoney(bettingMoney.multiply(new BigDecimal("1.5")));
            return;
        }
        if (isBlackJackDraw(mainPlayer, opponentPlayer)
                || isNormalDraw(mainPlayer, opponentPlayer)) {
            return;
        }
        if (isNormalWin(mainPlayer, opponentPlayer)) {
            winningMoney.addMoney(bettingMoney);
            return;
        }
        if (isBlackJackLose(opponentPlayer)) {
            winningMoney.subtractMoney(bettingMoney.multiply(new BigDecimal("1.5")));
            return;
        }
        winningMoney.subtractMoney(bettingMoney);
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
        return mainPlayer.getScore() == opponentPlayer.getScore();
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
