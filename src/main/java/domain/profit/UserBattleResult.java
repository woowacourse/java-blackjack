package domain.profit;

import domain.player.Dealer;
import domain.player.User;

public enum UserBattleResult {

    BLACKJACK_WIN(1.5),
    NORMAL_WIN(1.0),
    LOSE(-1.0),
    DRAW(0.0),
    ;

    private final double earningRate;

    UserBattleResult(double earningRate) {
        this.earningRate = earningRate;
    }

    // TODO: 라인 수 줄이기
    public static UserBattleResult compare(User user, Dealer dealer) {
        if (user.isBust()) {
            return UserBattleResult.LOSE;
        }
        if (dealer.isBust()) {
            return UserBattleResult.NORMAL_WIN;
        }
        if (user.isBlackjack() && dealer.isBlackjack()) {
            return UserBattleResult.DRAW;
        }
        if (user.isBlackjack()) {
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

    public double getEarningRate() {
        return earningRate;
    }
}
