package blackjack.model.blackjack_player.dealer.judgement;

import blackjack.model.blackjack_player.dealer.Dealer;
import blackjack.model.blackjack_player.player.Player;

public interface Judgement {

    boolean isDraw(Dealer dealer, Player player);

    boolean isDealerWin(Dealer dealer, Player player);

    int calculatePlayerReward(Player player);
}
