package blackjack.domain.game.winningstrategy;

import blackjack.domain.game.WinningResult;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public class BlackjackWinningStrategy implements WinningStrategy {

    @Override
    public WinningResult getResult(Dealer dealer, Player player) {
        if (dealer.isBlackjack() && !player.isBlackjack()) {
            return WinningResult.LOSE;
        }
        if (dealer.isBlackjack() && player.isBlackjack()) {
            return WinningResult.DRAW;
        }
        if (!dealer.isBlackjack() && player.isBlackjack()) {
            return WinningResult.WIN;
        }
        return WinningResult.NONE;
    }
}
