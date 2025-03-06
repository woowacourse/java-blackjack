package domain;

import domain.constant.WinDrawLose;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Players {

    private final List<Player> players;

    public Players(List<Player> players) {
        this.players = players;
    }

    public Map<Player, WinDrawLose> deriveResults(int dealerScore) {
        return players.stream()
                .collect(Collectors.toMap(
                        player -> player,
                        player -> player.compareTo(dealerScore)
                ));
    }
}
