package blackjack.domain.result;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;

public interface ResultPolicy {

    boolean judge(Player player, Dealer dealer);
}
