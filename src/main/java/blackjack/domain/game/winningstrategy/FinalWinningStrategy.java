package blackjack.domain.game.winningstrategy;

import blackjack.domain.game.WinningResult;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public class FinalWinningStrategy implements WinningStrategy {

    @Override
    public WinningResult getResult(Dealer dealer, Player player) {
        if (dealer.isBust() || (dealer.getScore() < player.getScore())) {
            return WinningResult.WIN;
        }
        if (dealer.getScore() > player.getScore()) {
            return WinningResult.LOSE;
        }
        if (dealer.getScore() == player.getScore()) {
            return WinningResult.DRAW;
        }
        return WinningResult.NONE;
    }
}
