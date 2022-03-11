package blackjack.service;

import blackjack.domain.Result;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.List;

public class GameScoreDecider {

    public GameScoreDecider(Dealer dealer, List<Player> players) {
        for (Player player : players) {
            player.decideMatchResult(dealer);
            dealer.decideMatchResult(player);
        }
    }
}
