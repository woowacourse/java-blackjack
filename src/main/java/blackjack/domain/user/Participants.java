package blackjack.domain.user;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Participants {

    private final Dealer dealer;
    private final List<Player> players;

    public Participants(final Dealer dealer, final List<Player> players) {
        validateDuplicatePlayerNames(players);

        this.dealer = dealer;
        this.players = players;
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

    private void validateDuplicatePlayerNames(List<Player> players) {
        Set<String> uniqueNames = new HashSet<>();
        for (Player player : players) {
            uniqueNames.add(player.getName());
        }

        if (players.size() != uniqueNames.size()) {
            throw new IllegalArgumentException("중복된 이름을 가진 플레이어가 있습니다.");
        }
    }
}
