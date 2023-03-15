package blackjack.domain.blackjack;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

class BlackJackRule {

    private static final int BUST_POINT = 21;

    int calculatePlayerProfit(final Player player, final Dealer dealer) {
        final ResultType result = calculatePlayerResult(dealer, player);
        return player.calculateProfit(result.getPlayerProfit());
    }

    private ResultType calculatePlayerResult(final Dealer dealer, final Player player) {
        if (dealer.hasBlackJack()) {
            return playWithBlackjack(player);
        }
        if (dealer.currentScore() > BUST_POINT) {
            return playWithBust(player);
        }
        return playWithScore(dealer, player);
    }

    private ResultType playWithBlackjack(final Player player) {
        if (player.hasBlackJack()) {
            return ResultType.TIE;
        }
        return ResultType.BLACKJACK_LOSE;
    }

    private ResultType playWithBust(final Player player) {
        if (player.currentScore() > BUST_POINT) {
            return ResultType.TIE;
        }
        if (player.hasBlackJack()) {
            return ResultType.BLACKJACK_WIN;
        }
        return ResultType.WIN;
    }

    private ResultType playWithScore(final Dealer dealer, final Player player) {
        if (player.hasBlackJack()) {
            return ResultType.BLACKJACK_WIN;
        }
        if (player.currentScore() > BUST_POINT || dealer.currentScore() > player.currentScore()) {
            return ResultType.LOSE;
        }
        return ResultType.WIN;
    }
}
