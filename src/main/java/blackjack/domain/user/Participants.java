package blackjack.domain.user;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Participants {

    private final Dealer dealer;
    private final List<Player> players;

    public Participants(final Dealer dealer, final List<Player> players) {
        validateOverOnePlayers(players);
        validateDuplicatePlayerNames(players);
        validatePlayerNumber(players);

        this.dealer = dealer;
        this.players = players;
    }

    private void validateOverOnePlayers(List<Player> players) {
        if (players.isEmpty()) {
            throw new IllegalArgumentException("플레이어는 한 명 이상 참가해야 합니다.");
        }
    }

    private void validateDuplicatePlayerNames(List<Player> players) {
        Set<String> uniqueNames = players.stream()
            .map(Player::getName)
            .collect(Collectors.toSet());

        if (players.size() != uniqueNames.size()) {
            throw new IllegalArgumentException("중복된 이름을 가진 플레이어가 있습니다.");
        }
    }

    private void validatePlayerNumber(List<Player> players) {
        if (players.size() > 25) {
            throw new IllegalArgumentException("플레이어는 25명까지만 참가 가능합니다.");
        }
    }

    public List<String> getPlayerNames() {
        return players.stream()
            .map(Player::getName)
            .toList();
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
