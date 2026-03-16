package domain;

import vo.GameResult;

public class GameJudge {
    private static final Integer BUST_THRESHOLD = 21;

    public GameResult judge(User user, Dealer dealer) {
        if (user.getScore() > BUST_THRESHOLD) {
            return GameResult.BUST;
        }
        if (dealer.isBlackjack() && !user.isBlackjack()) {
            return GameResult.LOSE;
        }
        if (user.isBlackjack() && !dealer.isBlackjack()) {
            return GameResult.BLACKJACK;
        }
        if (user.getScore() == dealer.getScore()) {
            return GameResult.PUSH;
        }
        if (dealer.getScore() > BUST_THRESHOLD || user.getScore() > dealer.getScore()) {
            return GameResult.WIN;
        }
        return GameResult.LOSE;
    }
}
