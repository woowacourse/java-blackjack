package domain.profit;

import domain.state.State;
import domain.state.StateType;

public enum UserBattleResult {

    BLACKJACK_WIN,
    NORMAL_WIN,
    LOSE,
    DRAW,
    ;

    public static UserBattleResult compare(State user, State dealer) {
        if (user.type() == StateType.BUST) {
            return UserBattleResult.LOSE;
        } else if (dealer.type() == StateType.BUST) {
            return UserBattleResult.NORMAL_WIN;
        }

        if (user.type() == StateType.BLACKJACK && dealer.type() == StateType.BLACKJACK) {
            return UserBattleResult.DRAW;
        } else if (user.type() == StateType.BLACKJACK) {
            return UserBattleResult.BLACKJACK_WIN;
        }
        return compareBySum(user.cards().computeOptimalSum(), dealer.cards().computeOptimalSum());
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
