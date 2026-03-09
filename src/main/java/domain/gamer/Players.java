package domain.gamer;

import domain.gamer.dto.PlayerHandDto;

import java.util.Collections;
import java.util.List;

public class Players {

    private final List<Player> players;

    private Players(List<Player> players) {
        this.players = players;
    }

    public static Players from(List<Player> players) {
        return new Players(players);
    }

    public void dealCardBundle(Dealer dealer) {
        for (Player player : players) {
            dealer.dealCardToPlayer(player);
        }
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

}
