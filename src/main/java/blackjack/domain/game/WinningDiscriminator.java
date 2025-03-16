package blackjack.domain.game;

import static blackjack.domain.game.WinningType.BLACKJACK_WIN;
import static blackjack.domain.game.WinningType.DEFEAT;
import static blackjack.domain.game.WinningType.DRAW;
import static blackjack.domain.game.WinningType.WIN;

import blackjack.domain.gambler.Dealer;
import blackjack.domain.gambler.Player;

public class WinningDiscriminator {
    public WinningType judgePlayerResult(final Dealer dealer, final Player player) {
        if (player.isBust()) {
            return DEFEAT;
        }
        if (dealer.isBust()) {
            return WIN;
        }
        if (player.isBlackjack() && !dealer.isBlackjack()) {
            return BLACKJACK_WIN;
        }
        return judgePlayerResultByScoreDifference(dealer, player);
    }

    private WinningType judgePlayerResultByScoreDifference(final Dealer dealer, final Player player) {
        int scoreDifference = dealer.calculateScoreDifference(player);
        if (scoreDifference > 0) {
            return DEFEAT;
        }
        if (scoreDifference < 0) {
            return WIN;
        }
        return DRAW;
    }
}
