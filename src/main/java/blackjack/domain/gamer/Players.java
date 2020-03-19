package blackjack.domain.gamer;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Players implements Iterable<Player> {

    private final List<Player> players;

    private Players(List<Player> players) {
        this.players = Collections.unmodifiableList(players);
    }

    public static Players of(List<String> names) {
        List<Player> players = names.stream()
                .map(Player::new)
                .collect(Collectors.toList());
        return new Players(players);
    }

    public List<String> getNames() {
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.toList());
    }

    public Player findPlayer(String name) {
        return players.stream()
                .filter(value -> value.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(name + "플레이어가 존재하지 않습니다."));
    }

    @Override
    public Iterator<Player> iterator() {
        return players.iterator();
    }
}
