package blackjack.domain.participant;

import java.util.Collections;
import java.util.List;

public class Participants {

    private final Dealer dealer;
    private final Players players;

    private Participants(Players players) {
        this.dealer = new Dealer();
        this.players = players;
    }

    public static Participants create(Players players) {
        return new Participants(players);
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Player getPlayer(Name name) {
        return players.findByName(name);
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players.getPlayers());
    }
}
