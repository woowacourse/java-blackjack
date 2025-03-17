package blackjack.model.game;

import blackjack.model.player.Dealer;
import blackjack.model.player.User;

public enum GameResult {

    BLACKJACK_WIN,
    WIN,
    DRAW,
    LOSE;

    GameResult() {}

    public static GameResult calculateResult(final User user, final Dealer dealer) {
        if (isUserBlackJackWin(user, dealer)) {
            return BLACKJACK_WIN;
        }
        if (isDraw(user, dealer)) {
            return DRAW;
        }
        if (isUserWin(user, dealer)) {
            return WIN;
        }
        return LOSE;
    }

    private static boolean isUserBlackJackWin(final User user, final Dealer dealer) {
        return user.isBlackjack() && !dealer.isBlackjack();
    }

    private static boolean isUserWin(final User user, final Dealer dealer) {
        int playerPoint = user.calculatePoint();
        int otherPlayerPoint = dealer.calculatePoint();
        if (!user.isBust() && dealer.isBust()) {
            return true;
        }
        return !user.isBust() && !dealer.isBust() && playerPoint > otherPlayerPoint;
    }

    private static boolean isDraw(final User user, final Dealer dealer) {
        if (user.isBlackjack() && dealer.isBlackjack()) {
            return true;
        }
        int userPoint = user.calculatePoint();
        int dealerPoint = dealer.calculatePoint();
        return !user.isBust() && !dealer.isBust() && (userPoint == dealerPoint);
    }

}
