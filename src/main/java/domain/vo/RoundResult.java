package domain.vo;

import domain.member.Dealer;
import domain.member.Player;

public enum RoundResult {

    WIN(1),
    LOSE(-1),
    DRAW(0);

    private final double profitRate;

    RoundResult(double profitRate) {
        this.profitRate = profitRate;
    }

    public static RoundResult judgeAgainst(Dealer dealer, Player player) {
        if (player.hasBust()) return RoundResult.LOSE;
        if (dealer.hasBust()) return RoundResult.WIN;
        if (player.isBiggerThan(dealer)) return RoundResult.WIN;
        if (dealer.isBiggerThan(player)) return RoundResult.LOSE;
        return RoundResult.DRAW;
    }

    public int calculateProfit(int bettingAmount) {
        return (int) (bettingAmount * profitRate);
    }
}
