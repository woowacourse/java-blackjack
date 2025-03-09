package blackjack.domain.user;

import java.util.Collections;
import java.util.List;

public class Participants {

    private final Dealer dealer;
    private final List<Player> players;

    public Participants(final Dealer dealer, final List<Player> players) {
        this.dealer = dealer;
        this.players = players;
    }

    public List<String> getPlayerNames() {
        return players.stream()
            .map(Player::getName)
            .toList();
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
