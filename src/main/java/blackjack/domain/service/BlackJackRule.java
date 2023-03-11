package blackjack.domain.service;

import blackjack.domain.blackjack.ResultType;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public class BlackJackRule {

    private static final int BUST_POINT = 21;

    public int calculatePlayerProfit(final Player player, final Dealer dealer) {
        final ResultType result = calculateDealerResult(dealer, player);
        return (int) (player.calculateProfit(result.getPlayerProfit()) * result.getPlayerProfit());
    }

    public ResultType calculateDealerResult(final Dealer dealer, final Player player) {
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
        return ResultType.BLACKJACK_WIN;
    }

    private ResultType playWithBust(final Player player) {
        if (player.currentScore() > BUST_POINT) {
            return ResultType.TIE;
        }
        if (player.hasBlackJack()) {
            return ResultType.BLACKJACK_LOSE;
        }
        return ResultType.LOSE;
    }

    private ResultType playWithScore(final Dealer dealer, final Player player) {
        if (player.hasBlackJack()) {
            return ResultType.BLACKJACK_LOSE;
        }
        if (player.currentScore() > BUST_POINT || dealer.currentScore() > player.currentScore()) {
            return ResultType.WIN;
        }
        return ResultType.LOSE;
    }
}
