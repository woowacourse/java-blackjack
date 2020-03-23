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

    public void calculateWinningMoney(Player mainPlayer, Player opponentPlayer) {
        BigDecimal bettingMoney = getUserBettingMoney(mainPlayer, opponentPlayer);
        ResultType resultType = ResultType.of(mainPlayer, opponentPlayer);
        BigDecimal rewardRate = new BigDecimal(resultType.getRewardRate());

        winningMoney = winningMoney.addMoney(bettingMoney.multiply(rewardRate));
    }

    private BigDecimal getUserBettingMoney(Player mainPlayer, Player opponentPlayer) {
        Money userMoney;

        if (mainPlayer instanceof User) {
            userMoney = ((User) mainPlayer).getBettingMoney();
            return userMoney.getMoney();
        }
        userMoney = ((User) opponentPlayer).getBettingMoney();
        return userMoney.getMoney();
    }

    public BigDecimal getWinningMoney() {
        return winningMoney.getMoney();
    }
}
