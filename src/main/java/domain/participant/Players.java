package domain.participant;

import domain.deck.DeckStrategy;
import domain.game.GamePoint;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class Players {

    public static final int MIN_COUNT = 1;

    private final List<Player> players;

    private Players(final List<Player> players) {
        this.players = new ArrayList<>(players);
    }

    public static Players create(final List<Name> names, final List<Integer> bets) {
        validatePlayersCount(names);
        validateDuplicateName(names);
        final List<Player> players = IntStream.range(0, names.size())
                .mapToObj(index -> Player.of(names.get(index), bets.get(index)))
                .collect(Collectors.toList());

        return new Players(players);
    }

    private static void validatePlayersCount(final List<Name> names) {
        if (names.size() < MIN_COUNT) {
            throw new IllegalArgumentException(
                    String.format("%d명 이상일 때 게임을 실행할 수 있습니다.", MIN_COUNT));
        }
    }

    private static void validateDuplicateName(final List<Name> names) {
        if (names.size() != new HashSet<>(names).size()) {
            throw new IllegalArgumentException("중복된 이름은 사용할 수 없습니다.");
        }
    }

    public void takeCard(final DeckStrategy deck, final int count) {
        for (Player player : players) {
            player.takeInitialCards(deck, count);
        }
    }

    public List<Player> findPlayersLowerThan(final GamePoint gamePoint) {
        return players.stream()
                .filter(player -> player.hasLowerThan(gamePoint))
                .collect(Collectors.toUnmodifiableList());
    }

    public List<Player> findPlayerSameAs(final GamePoint gamePoint) {
        return players.stream()
                .filter(player -> player.hasSameAs(gamePoint))
                .collect(Collectors.toUnmodifiableList());
    }

    public List<Player> findPlayerGreaterThan(final GamePoint gamePoint) {
        return players.stream()
                .filter(player -> player.hasGreaterThan(gamePoint))
                .collect(Collectors.toUnmodifiableList());
    }

    public List<Player> getPlayers() {
        return List.copyOf(players);
    }
}
