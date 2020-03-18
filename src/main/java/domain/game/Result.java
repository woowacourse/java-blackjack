package domain.game;

import domain.betting.BettingLog;
import domain.betting.BettingMoney;
import domain.player.Player;
import domain.player.User;

import java.math.BigDecimal;

public class Result {
    private static final int ONE = 1;
    private final String name;
    private int winCount;
    private int loseCount;
    private BettingMoney bettingMoney;

    public Result(final String name) {
        this.name = name;
        this.winCount = 0;
        this.loseCount = 0;
        this.bettingMoney = new BettingMoney("0");
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
        BigDecimal bettingMoney = userBettingLog.getBettingMoney();
        if (mainPlayer instanceof User && isBlackJackWin(mainPlayer, opponentPlayer)) {
            this.bettingMoney.addBettingMoney(bettingMoney.multiply(new BigDecimal("1.5")));
            return;
        }
        if (isBlackJackDraw(mainPlayer, opponentPlayer) || isDraw(mainPlayer, opponentPlayer)) {
            return;
        }
        if (isNormalWin(mainPlayer, opponentPlayer)) {
            this.bettingMoney.addBettingMoney(bettingMoney);
            return;
        }
        if (opponentPlayer instanceof User && opponentPlayer.isBlackJack()) {
            this.bettingMoney.subtractBettingMoney(bettingMoney.multiply(new BigDecimal("1.5")));
            return;
        }
        this.bettingMoney.subtractBettingMoney(bettingMoney);
    }

    private boolean isBlackJackWin(Player mainPlayer, Player opponentPlayer) {
        return mainPlayer.isWin(opponentPlayer) && mainPlayer.isBlackJack() && !opponentPlayer.isBlackJack();
    }

    private boolean isBlackJackDraw(Player mainPlayer, Player opponentPlayer) {
        return mainPlayer.isWin(opponentPlayer) && mainPlayer.isBlackJack() && opponentPlayer.isBlackJack();
    }

    private boolean isDraw(Player mainPlayer, Player opponentPlayer) {
        return mainPlayer.getScore() == opponentPlayer.getScore();
    }

    private boolean isNormalWin(Player mainPlayer, Player opponentPlayer) {
        return mainPlayer.isWin(opponentPlayer);
    }

    public BigDecimal getBettingMoney() {
        return bettingMoney.getBettingMoney();
    }
}
