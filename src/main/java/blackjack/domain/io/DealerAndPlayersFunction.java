package blackjack.domain.io;

import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import java.util.List;

@FunctionalInterface
public interface DealerAndPlayersFunction {

    void execute(Dealer dealer, List<Player> players);
}