package blackjack.domain.participants;

import static blackjack.domain.ExceptionMessage.INVALID_PLAYER_NAMES_DUPLICATED;
import static blackjack.domain.ExceptionMessage.INVALID_PLAYER_NAMES_RESERVED_FORMAT;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Participants {

    private final Dealer dealer;
    private final Players players;

    private Participants(final Dealer dealer, final List<Player> players) {
        this.dealer = dealer;
        this.players = new Players(players);
    }

    public static Participants of(final Dealer dealer, final List<Player> players) {
        validatePlayerNames(players);
        return new Participants(dealer, players);
    }

    private static void validatePlayerNames(final List<Player> players) {
        final List<String> names = players.stream()
                .map(Player::getName)
                .collect(Collectors.toList());
        if (names.size() != new HashSet<>(names).size()) {
            throw new IllegalArgumentException(INVALID_PLAYER_NAMES_DUPLICATED);
        }
        if (names.contains(Dealer.NAME)) {
            throw new IllegalArgumentException(String.format(INVALID_PLAYER_NAMES_RESERVED_FORMAT, Dealer.NAME));
        }
    }

    public List<Player> findHitAblePlayers() {
        return players.findHitAblePlayers();
    }

    public Dealer dealer() {
        return dealer;
    }

    public List<Player> players() {
        return players.players();
    }
}
