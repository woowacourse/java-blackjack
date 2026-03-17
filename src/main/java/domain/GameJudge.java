package domain;

import vo.GameResult;

public class GameJudge {
    public static GameResult judge(User user, Dealer dealer) {
        if (user.isBust()) {
            return GameResult.BUST;
        }
        if (dealer.isBlackjack() && !user.isBlackjack()) {
            return GameResult.LOSE;
        }
        if (user.isBlackjack() && !dealer.isBlackjack()) {
            return GameResult.BLACKJACK;
        }
        if (user.hasSameScore(dealer)) {
            return GameResult.PUSH;
        }
        if (dealer.isBust() || user.hasHigherScoreThan(dealer)) {
            return GameResult.WIN;
        }
        return GameResult.LOSE;
    }
}
