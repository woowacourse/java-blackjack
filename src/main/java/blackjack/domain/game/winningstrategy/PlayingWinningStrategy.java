package blackjack.domain.game.winningstrategy;

import blackjack.domain.game.WinningResult;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public class PlayingWinningStrategy implements WinningStrategy {

    @Override
    public WinningResult getResult(Dealer dealer, Player player) {
        if (player.isBurst()) {
            return WinningResult.LOSE;
        }
        return WinningResult.NONE;
    }
}
