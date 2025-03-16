package domain.profit;

import domain.player.Dealer;
import domain.player.User;

public enum UserBattleResult {

    BLACKJACK_WIN,
    NORMAL_WIN,
    LOSE,
    DRAW,
    ;

    public static UserBattleResult compare(User user, Dealer dealer) {
        if (user.isBust()) {
            return UserBattleResult.LOSE;
        } else if (dealer.isBust()) {
            return UserBattleResult.NORMAL_WIN;
        }

        if (user.isBlackjack() && dealer.isBlackjack()) {
            return UserBattleResult.DRAW;
        } else if (user.isBlackjack()) {
            return UserBattleResult.BLACKJACK_WIN;
        }
        return compareBySum(user.computeOptimalSum(), dealer.computeOptimalSum());
    }

    private static UserBattleResult compareBySum(int sum1, int sum2) {
        if (sum1 > sum2) {
            return NORMAL_WIN;
        }
        if (sum1 < sum2) {
            return LOSE;
        }
        return DRAW;
    }
}
