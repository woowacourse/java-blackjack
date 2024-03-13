package blackjack.domain.game.result;

import blackjack.domain.betting.Money;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public interface GameResult {
    boolean isSatisfy(Dealer dealer, Player player);

    Money calculatePrize(Money bet);
}
