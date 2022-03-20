package blackjack_statepattern.participant;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Players {

    private final List<Player> players;

    private Players(List<Player> players) {
        this.players = players;
    }

    public static Players create(Map<String, BetMoney> playerNameAndBetMoney) {
        return new Players(playerNameAndBetMoney.entrySet().stream()
                .map(eachPlayer -> new Player(eachPlayer.getKey(), eachPlayer.getValue()))
                .collect(Collectors.toList()));
    }

    public List<Player> getPlayers() {
        return players;
    }
}
