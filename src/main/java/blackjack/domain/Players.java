package blackjack.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Players {
    private final List<Player> players = new ArrayList<>();

    public Players(List<String> names) {
        for (String name : names) {
            players.add(new Player(name));
        }
    }

    public Players(List<String> names, List<BigDecimal> bettingAmounts) {
        int playerCount = names.size();
        for (int i = 0; i < playerCount; i++) {
            players.add(new Player(names.get(i), new BettingAmount(bettingAmounts.get(i))));
        }
    }

    public void receiveCard(Deck deck) {
        for (Player player : players) {
            player.receiveCard(deck.draw());
        }
    }

    public List<Player> getPlayers() {
        return players;
    }
}
