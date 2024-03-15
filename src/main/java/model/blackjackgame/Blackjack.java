package model.blackjackgame;

import java.util.HashMap;
import java.util.Map;
import model.participants.dealer.Dealer;
import model.participants.player.Player;
import model.participants.player.Players;

public class Blackjack {

    private final boolean dealer;
    private final Map<Player, Boolean> playersStatus;

    public Blackjack(Dealer dealer) {
        this.dealer = dealer.isBlackjack();
        this.playersStatus = new HashMap<>();
    }

    public void playerBlackjackStatus(Players players) {
        for (Player player : players.getPlayers()) {
            playersStatus.put(player, player.isBlackjack());
        }
    }

    public boolean getDealer() {
        return dealer;
    }

    public Map<Player, Boolean> getPlayersStatus() {
        return playersStatus;
    }
}
