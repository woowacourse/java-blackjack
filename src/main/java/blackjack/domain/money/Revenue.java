package blackjack.domain.money;

import blackjack.domain.gameresult.WinningResult;
import blackjack.domain.participant.Player;

public class Revenue {

    private static final int PUSH_REVENUE = 0;
    private static final int LOSE_REVENUE = -1;
    private static final double BLACKJACK_REVENUE = 1.5;

    private final int revenue;

    private Revenue(int revenue) {
        this.revenue = revenue;
    }

    public static Revenue generateRevenue(Player player, WinningResult winningResult) {
        if (player.judgeBlackjack() && winningResult.equals(WinningResult.WIN)) {
            return new Revenue(calculatePlayerBlackjackWin(player.getBettingMoney()));
        }
        if (winningResult.equals(WinningResult.WIN)) {
            return new Revenue(calculatePlayerNotBlackjackWin(player.getBettingMoney()));
        }
        if (winningResult.equals(WinningResult.PUSH)) {
            return new Revenue(calculatePlayerPush());
        }
        return new Revenue(calculatePlayerLose(player.getBettingMoney()));
    }

    public int getRevenue() {
        return revenue;
    }

    private static int calculatePlayerBlackjackWin (BettingMoney bettingMoney) {
        return (int) (bettingMoney.getMoney() * BLACKJACK_REVENUE);
    }

    private static int calculatePlayerNotBlackjackWin(BettingMoney bettingMoney) {
        return bettingMoney.getMoney();
    }

    private static int calculatePlayerPush() {
        return PUSH_REVENUE;
    }

    private static int calculatePlayerLose(BettingMoney bettingMoney) {
        return LOSE_REVENUE * bettingMoney.getMoney();
    }
}
