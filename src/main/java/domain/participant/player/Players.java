package domain.participant.player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

import domain.participant.attributes.Bet;
import domain.participant.attributes.Name;

public class Players {

    private static final int MIN_SIZE = 1;
    private static final int MAX_SIZE = 8;

    private final List<Player> players;

    private Players(final Collection<Player> players) {
        this.players = List.copyOf(players);
    }

    public static Players from(final Collection<Player> players) {
        validateSize(players);
        return new Players(players);
    }

    public static Players of(final List<Name> names, final List<Bet> bets) {
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < names.size(); i++) {
            players.add(new Player(names.get(i), bets.get(i)));
        }
        return new Players(players);
    }

    private static void validateSize(final Collection<Player> players) {
        if (players.isEmpty() || players.size() > MAX_SIZE) {
            throw new IllegalArgumentException("플레이어의 수는 최소 %d명 최대 %d명입니다 : 현재 %d명"
                    .formatted(MIN_SIZE, MAX_SIZE, players.size()));
        }
    }

    public void forEach(final Consumer<Player> action) {
        players.forEach(action);
    }

    public Stream<Player> stream() {
        return players.stream();
    }
}
