package domain.profit;

import domain.state.State;
import domain.state.StateType;

public enum UserBattleResult {

    BLACKJACK_WIN,
    NORMAL_WIN,
    LOSE,
    DRAW,
    ;

    public static UserBattleResult determineUserBattleResult(State user, State dealer) {
        if (blackjackExists(user, dealer)) {
            return determineByBlackjack(user, dealer);
        }
        if (bustExists(user, dealer)) {
            return determineByBust(user, dealer);
        }
        return compareBySum(user.cards().computeOptimalSum(), dealer.cards().computeOptimalSum());
    }

    private static boolean blackjackExists(State user, State dealer) {
        return user.type() == StateType.BLACKJACK || dealer.type() == StateType.BLACKJACK;
    }

    private static boolean bustExists(State user, State dealer) {
        return user.type() == StateType.BUST || dealer.type() == StateType.BUST;
    }

    private static UserBattleResult determineByBlackjack(State user, State dealer) {
        if (user.type() == StateType.BLACKJACK && dealer.type() == StateType.BLACKJACK) {
            return DRAW;
        }
        if (user.type() == StateType.BLACKJACK) {
            return BLACKJACK_WIN;
        }
        if (dealer.type() == StateType.BLACKJACK) {
            return LOSE;
        }
        throw new IllegalStateException("블랙잭 상태 여부로 승부를 결정할 수 없습니다.");
    }

    private static UserBattleResult determineByBust(State user, State dealer) {
        if (user.type() == StateType.BUST) {
            return LOSE;
        }
        if (dealer.type() == StateType.BUST) {
            return NORMAL_WIN;
        }
        throw new IllegalStateException("버스트 상태 여부로 승부를 결정할 수 없습니다.");
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
