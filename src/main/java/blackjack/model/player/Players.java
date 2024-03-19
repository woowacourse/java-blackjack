package blackjack.model.player;

import blackjack.model.cardgenerator.CardGenerator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Players {
    private static final String INVALID_NAMES_COUNT = "플레이어 수는 1명 이상이어야 합니다.";
    private static final String DUPLICATED_NAMES = "플레이어 이름은 중복될 수 없습니다.";

    private final List<Player> players;

    public Players(final List<PlayerName> playerNames) {
        validate(playerNames);
        this.players = playerNames.stream()
                .map(Player::new)
                .toList();
    }

    private void validate(final List<PlayerName> playerNames) {
        validatePlayerNamesCount(playerNames);
        validateDuplicatedPlayerNames(playerNames);
    }

    private void validatePlayerNamesCount(final List<PlayerName> playerNames) {
        if (playerNames.isEmpty()) {
            throw new IllegalArgumentException(INVALID_NAMES_COUNT);
        }
    }

    private void validateDuplicatedPlayerNames(final List<PlayerName> playerNames) {
        Set<PlayerName> uniquePlayerNames = new HashSet<>(playerNames);
        if (uniquePlayerNames.size() != playerNames.size()) {
            throw new IllegalArgumentException(DUPLICATED_NAMES);
        }
    }

    public void dealCards(final CardGenerator cardGenerator) {
        players.forEach(player -> player.dealCards(cardGenerator));
    }

    public boolean isAllBlackJack() {
        return players.stream()
                .allMatch(Player::isBlackjack);
    }

    public List<PlayerName> getNames() {
        return players.stream()
                .map(Player::getName)
                .toList();
    }

    public List<Player> getPlayers() {
        return players;
    }
}
