package domain;

import java.util.List;
import java.util.stream.Collectors;

public class Players {
    //인원제한 적당히... 5명!
    private final List<Player> players;

    public Players(List<String> playerNames) {
        this.players = createPlayers(playerNames);
    }

    private List<Player> createPlayers(List<String> players) {
        return players.stream()
                .map(name -> new Player(new PlayerName(name), new Cards()))
                .collect(Collectors.toList());
    }
}
