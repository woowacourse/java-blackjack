package blackjackgame.model.blackjackgame;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import blackjackgame.model.participants.dealer.Dealer;
import blackjackgame.model.participants.player.Player;
import blackjackgame.model.participants.player.Players;

public class Blackjack {

    private final boolean isDealerBlackjack;
    private final Map<Player, Boolean> blackjackStatusOfPlayers;

    public Blackjack(Dealer dealer, Players players) {
        this.isDealerBlackjack = dealer.isBlackjack();
        this.blackjackStatusOfPlayers = new HashMap<>();
        createPlayerBlackjackStatus(players);
    }

    private void createPlayerBlackjackStatus(Players players) {
        for (Player player : players.getPlayers()) {
            blackjackStatusOfPlayers.put(player, player.isBlackjack());
        }
    }

    public boolean isDealerBlackjack() {
        return isDealerBlackjack;
    }

    public Boolean blackjackStatusOfPlayer(Player player) {
        return blackjackStatusOfPlayers.entrySet()
                .stream()
                .filter(entry -> isSameNamePlayer(entry.getKey(), player))
                .map(Entry::getValue)
                .findFirst()
                .orElse(false);
    }

    private boolean isSameNamePlayer(Player player1, Player player2) {
        return player1.getName().equals(player2.getName());
    }
}
