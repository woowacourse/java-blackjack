package domain.participant;

import domain.game.Deck;
import domain.game.GamePoint;

import java.util.List;
import java.util.stream.Collectors;

public final class Players {

    public static final int MIN_COUNT = 2;

    private final List<Player> players;

    private Players(final List<Player> players) {
        this.players = players;
    }

    public static Players create(final List<Name> names) {
        validatePlayersCount(names);
        final List<Player> players = names.stream()
                .map(Player::of)
                .collect(Collectors.toList());
        return new Players(players);
    }

    private static void validatePlayersCount(final List<Name> names) {
        if (names.size() < MIN_COUNT) {
            throw new IllegalArgumentException("두 명 이상일 때 게임을 실행할 수 있습니다.");
        }
    }

    public void takeCard(final Deck deck, final int count) {
        for (Player player : players) {
            player.takeCard(deck, count);
        }
    }

    public List<Player> getPlayers() {
        return List.copyOf(players);
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
}
