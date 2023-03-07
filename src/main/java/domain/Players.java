package domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

// TODO: 2023-03-07 정적 팩터리 메서드에 BetAmount 추가
public class Players {
    private final List<Player> players;

    private Players(List<Player> players) {
        this.players = players;
    }

    public static Players from(Names playerNames) {
        return new Players(playerNames.getNames()
                .stream()
                .map(Player::from)
                .collect(Collectors.toList()));
    }

    public static Players from(List<Player> players) {
        return new Players(players);
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
