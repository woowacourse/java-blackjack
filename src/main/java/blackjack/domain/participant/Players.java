package blackjack.domain.participant;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Players {
    private static final int MINIMUM_PLAYER_SIZE = 1;
    private static final int MAXIMUM_PLAYER_SIZE = 6;

    private final List<Player> players;

    public Players(final List<String> playerNames) {
        validatePlayers(playerNames);
        this.players = mapPlayer(playerNames);
    }

    private void validatePlayers(final List<String> playerNames) {
        validateDuplicatedName(playerNames);
        validatePlayerSize(playerNames);
    }

    private void validateDuplicatedName(final List<String> playerNames) {
        if (playerNames.size() != new HashSet<>(playerNames).size()) {
            throw new IllegalArgumentException("플레이어 이름 중 중복된 이름이 존재할 수 없습니다.");
        }
    }

    private void validatePlayerSize(final List<String> playerNames) {
        final int size = playerNames.size();

        if (size < MINIMUM_PLAYER_SIZE || size > MAXIMUM_PLAYER_SIZE) {
            throw new IllegalArgumentException(
                    String.format("게임에 참여하는 플레이어는 %d ~ %d명이어야 합니다.", MINIMUM_PLAYER_SIZE, MAXIMUM_PLAYER_SIZE));
        }
    }

    private List<Player> mapPlayer(final List<String> playerNames) {
        return playerNames.stream()
                .map(Player::new)
                .toList();
    }

    public int count() {
        return players.size();
    }

    public Player findPlayerByIndex(final int playerIndex) {
        return players.get(playerIndex);
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
