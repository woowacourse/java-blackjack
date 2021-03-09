package blackjack.domain.gamer;

import blackjack.domain.money.Money;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Players implements Iterable<Player> {
    private final List<Player> players;

    private Players(List<Player> players) {
        this.players = players;
    }

    public static Players of(List<String> names, List<Money> money) {
        List<Player> players = IntStream.range(0, names.size())
                .mapToObj(i -> new Player(names.get(i), money.get(i)))
                .collect(Collectors.toList());
        return new Players(players);
    }

    public List<String> getNames() {
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.toList());
    }

    @Override
    public Iterator<Player> iterator() {
        return players.iterator();
    }
}
