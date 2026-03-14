package domain;

import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

public class Players {

    private final List<Player> players;

    public Players(List<Player> players) {
        this.players = Collections.unmodifiableList(players);
    }

    public void receiveCardAll(Supplier<Card> cardSupplier) {
        for(Player player : players) {
            Card card = cardSupplier.get();
            player.receiveCard(card);
        }
    }

    public List<Player> getPlayers() {
        return List.copyOf(players);
    }
}
