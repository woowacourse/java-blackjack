package model.blackjackgame;

import java.util.HashMap;
import java.util.Map;
import model.participants.dealer.Dealer;
import model.participants.player.Player;
import model.participants.player.Players;

public class Blackjack {

    private final boolean isDealer;
    private final Map<Player, Boolean> blackjackStatusOfPlayers;

    public Blackjack(Dealer dealer) {
        this.isDealer = dealer.isBlackjack();
        this.blackjackStatusOfPlayers = new HashMap<>();
    }

    public void createPlayerBlackjackStatus(Players players) {
        for (Player player : players.getPlayers()) {
            blackjackStatusOfPlayers.put(player, player.isBlackjack());
        }
    }

    public boolean isDealer() {
        return isDealer;
    }

    public Map<Player, Boolean> getBlackjackStatusOfPlayers() {
        return blackjackStatusOfPlayers;
    }
}
