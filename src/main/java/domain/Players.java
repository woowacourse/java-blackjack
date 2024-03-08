package domain;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

public class Players {
    private static final int UNIQUE_COUNT = 1;
    private static final int MIN_PLAYER_COUNT = 1;
    private static final int MAX_PLAYER_COUNT = 6;

    private final List<Player> players;

    public Players(List<String> playerNames) {
        validatePlayerCount(playerNames);
        validateDuplicatedName(playerNames);
        this.players = generatePlayers(playerNames);
    }

    private void validatePlayerCount(List<String> playerNames) {
        int playerCount = playerNames.size();
        if (playerCount < MIN_PLAYER_COUNT || MAX_PLAYER_COUNT < playerCount) {
            throw new IllegalArgumentException(
                    String.format("플레이어의 수는 %d ~ %d명이어야 합니다.", MIN_PLAYER_COUNT, MAX_PLAYER_COUNT)
            );
        }
    }

    private void validateDuplicatedName(List<String> playerNames) {
        Map<String, Long> nameCounts = getNameCounts(playerNames);
        Optional<String> duplicatedName = findDuplicatedName(nameCounts);
        if (duplicatedName.isPresent()) {
            throw new IllegalArgumentException(
                    String.format("rejected value: %s - 중복된 이름이 존재합니다.", duplicatedName.get())
            );
        }
    }

    private Optional<String> findDuplicatedName(Map<String, Long> nameCounts) {
        return nameCounts.entrySet()
                .stream()
                .filter(entry -> entry.getValue() > UNIQUE_COUNT)
                .map(Entry::getKey)
                .findFirst();
    }

    private Map<String, Long> getNameCounts(List<String> playerNames) {
        return playerNames.stream()
                .collect(Collectors.groupingBy(name -> name, Collectors.counting()));
    }

    private List<Player> generatePlayers(List<String> playerNames) {
        return playerNames.stream()
                .map(Player::new)
                .toList();
    }

    public void giveCardToPlayer(int playerIndex, Card card) {
        Player player = players.get(playerIndex);
        player.add(card);
    }

    public int count() {
        return this.players.size();
    }

    public Player getPlayerByIndex(int index) { // todo index validation
        return this.players.get(index);
    }

    public List<String> getPlayerNames() {
        return players.stream()
                .map(Player::getName)
                .toList();
    }
}
