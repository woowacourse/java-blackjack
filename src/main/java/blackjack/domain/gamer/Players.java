package blackjack.domain.gamer;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Players implements Iterable<Player> {

    private static final String NAME_SPLITTER = ",";

    private List<Player> players;

    private Players(List<Player> players) {
        this.players = players;
    }

    public static Players ofComma(String names) {
        List<Player> players = Arrays.stream(names.split(NAME_SPLITTER))
                .map(String::trim)
                .map(Player::new)
                .collect(Collectors.toList());
        return new Players(players);
    }

    public String getNames() {
        List<String> names = players.stream().map(Player::getName).collect(Collectors.toList());
        return String.join(", ", names);
    }

    @Override
    public Iterator<Player> iterator() {
        return players.iterator();
    }
}
