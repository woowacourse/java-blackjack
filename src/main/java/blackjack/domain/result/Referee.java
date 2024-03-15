package blackjack.domain.result;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public class Referee {
    private static final Referee INSTANCE = new Referee();

    private Referee() {
    }

    public static Referee getInstance() {
        return INSTANCE;
    }

    public HandResult getPlayerResult(Player player, Dealer dealer) {
        if (player.isBust()) {
            return HandResult.LOSE;
        }
        if (dealer.isBust()) {
            return HandResult.WIN;
        }
        return getPlayerResultWithBlackjack(player, dealer);
    }

    private HandResult getPlayerResultWithBlackjack(Player player, Dealer dealer) {
        if (player.isBlackjack() && dealer.isBlackjack()) {
            return HandResult.DRAW;
        }
        if (player.isBlackjack() && !dealer.isBlackjack()) {
            return HandResult.BLACKJACK;
        }
        if (!player.isBlackjack() && dealer.isBlackjack()) {
            return HandResult.LOSE;
        }
        return getPlayerResultWithScore(player.getScore(), dealer.getScore());
    }

    private HandResult getPlayerResultWithScore(int playerScore, int dealerScore) {
        if (playerScore == dealerScore) {
            return HandResult.DRAW;
        }
        if (playerScore < dealerScore) {
            return HandResult.LOSE;
        }
        return HandResult.WIN;
    }
}
