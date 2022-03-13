package blackjack.domain.game.winningstrategy;

import blackjack.domain.game.WinningResult;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public interface WinningStrategy {
    WinningResult getResult(Dealer dealer, Player player);
}
