package domain.profit;

import domain.state.Blackjack;
import domain.state.Bust;
import domain.state.State;

public enum UserBattleResult {

    BLACKJACK_WIN,
    NORMAL_WIN,
    LOSE,
    DRAW,
    ;

    public static UserBattleResult determineUserBattleResult(State user, State dealer) {
        if (!user.isFinished() || !dealer.isFinished()) {
            throw new IllegalStateException("유저나 딜러가 종료 상태가 아닙니다.");
        }
        if (Blackjack.isBlackjack(user.cards()) || Blackjack.isBlackjack(dealer.cards())) {
            return determineByBlackjack(user, dealer);
        }
        if (Bust.isBust(user.cards()) || Bust.isBust(dealer.cards())) {
            return determineByBust(user, dealer);
        }
        return compareBySum(user.cards().computeOptimalSum(), dealer.cards().computeOptimalSum());
    }

    private static UserBattleResult determineByBlackjack(State user, State dealer) {
        if (Blackjack.isBlackjack(user.cards()) && Blackjack.isBlackjack(dealer.cards())) {
            return DRAW;
        }
        if (Blackjack.isBlackjack(user.cards())) {
            return BLACKJACK_WIN;
        }
        if (Blackjack.isBlackjack(dealer.cards())) {
            return LOSE;
        }
        throw new IllegalStateException("블랙잭 상태 여부로 승부를 결정할 수 없습니다.");
    }

    private static UserBattleResult determineByBust(State user, State dealer) {
        if (Bust.isBust(user.cards())) {
            return LOSE;
        }
        if (Bust.isBust(dealer.cards())) {
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
