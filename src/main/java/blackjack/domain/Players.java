package blackjack.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Players {

    private final List<Player> players;

    private Players(final List<Player> players) {
        this.players = players;
    }

    public static Players from(final List<String> names) {
        final List<Player> players = new ArrayList<>();
        for (final String name : names) {
            players.add(Player.from(name));
        }
        return new Players(players);
    }

    public void drawInitialHand(final Dealer dealer) {
        for (final Player player : players) {
            player.draw(dealer.drawPlayerCard());
            player.draw(dealer.drawPlayerCard());
        }
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
