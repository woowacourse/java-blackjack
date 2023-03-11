package blackjack.domain.participants;

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
        validatePlayerNames(dealer, players);
        return new Participants(dealer, players);
    }

    private static void validatePlayerNames(final Dealer dealer, final List<Player> players) {
        final List<String> names = players.stream()
                .map(Player::getName)
                .collect(Collectors.toList());
        final String dealerName = dealer.getName();
        if (names.size() != new HashSet<>(names).size()) {
            throw new IllegalArgumentException("플레이어 이름은 중복될 수 없습니다.");
        }
        if (names.contains(dealerName)) {
            throw new IllegalArgumentException("플레이어 이름은 딜러 이름(" + dealerName + ")과 중복될 수 없습니다.");
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
