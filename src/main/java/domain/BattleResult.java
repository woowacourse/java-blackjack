package domain;

import domain.player.Dealer;
import domain.player.User;

public enum BattleResult {

    BLACKJACK,
    NORMAL_WIN,
    LOSE,
    DRAW,
    ;

    public static BattleResult fight(Dealer dealer, User user) {
        if (user.isBust()) {
            return BattleResult.LOSE;
        }
        if (dealer.isBust()) {
            return BattleResult.NORMAL_WIN;
        }
        if (user.isBlackjack() && dealer.isBlackjack()) {
            return BattleResult.DRAW;
        }
        if (user.isBlackjack()) {
            return BattleResult.BLACKJACK;
        }
        return compareBySum(user.computeOptimalSum(), dealer.computeOptimalSum());
    }

    private static BattleResult compareBySum(int sum1, int sum2) {
        if (sum1 > sum2) {
            return NORMAL_WIN;
        }
        if (sum1 < sum2) {
            return LOSE;
        }
        return DRAW;
    }
}
