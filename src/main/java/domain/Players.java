package domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Players {
    private final List<Player> players;

    public Players(final List<Player> players) {
        this.players = players;
    }

    public static Players from(String[] names) {
        return new Players(Arrays.stream(names).map(name -> new Participant(new Name(name))).collect(Collectors.toList()));
    }

    public Player getDealer() {
        return players.stream().filter(player -> player.getName().equals("딜러")).findAny().orElseThrow();
    }

    public List<Player> getParticipants() {
        return players.stream().filter(player -> !player.getName().equals("딜러")).collect(Collectors.toList());
    }

    public List<Player> getPlayers() {
        return players;
    }
}
