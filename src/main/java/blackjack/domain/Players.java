package blackjack.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Players {

    private final List<Player> players;

    private Players(final List<String> playerNames) {
        validatePlayerNamesDuplicated(playerNames);
        this.players = playerNames.stream()
                .map(Player::new)
                .collect(Collectors.toUnmodifiableList());
    }

    private static void validatePlayerNamesDuplicated(final List<String> playerNames) {
        final Set<String> validNames = new HashSet<>(playerNames);
        if (validNames.size() != playerNames.size()) {
            throw new IllegalArgumentException("플레이어명은 중복될 수 없습니다.");
        }
    }

    public static Players startWithTwoCards(final List<String> names, final Deck deck) {
        Players players = new Players(names);
        players.distributeCards(deck);
        return players;
    }

    private void distributeCards(Deck deck) {
        for (Player player : players) {
            player.drawCard(deck);
            player.drawCard(deck);
        }
    }

    public List<Player> getStatuses() {
        return List.copyOf(players);
    }
}
