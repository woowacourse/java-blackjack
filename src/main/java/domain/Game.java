package domain;

import java.util.List;

public class Game {

    private final Rule rule;

    public Game(Rule rule) {
        this.rule = rule;
    }

    public void decideResult(DealerCards dealer, List<PlayerCards> players) {
        players.forEach(player -> decideResult(dealer, player));
    }

    private void decideResult(DealerCards dealer, PlayerCards player) {
        Status dealerStatus = rule.decideStatus(dealer, player);
        Status playerStatus = rule.decideStatus(player, dealer);
        dealer.updateStatus(dealerStatus);
        player.updateStatus(playerStatus);
    }
}
