package domain.participant;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

import domain.card.Card;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

public class Players {
    private static final int MIN_PLAYER_COUNT = 1;
    private static final int MAX_PLAYER_COUNT = 6;
    private static final int UNIQUE_COUNT = 1;

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

    private Map<String, Long> getNameCounts(List<String> playerNames) {
        return playerNames.stream()
                .collect(groupingBy(name -> name, counting()));
    }

    private Optional<String> findDuplicatedName(Map<String, Long> nameCounts) {
        return nameCounts.entrySet()
                .stream()
                .filter(entry -> entry.getValue() > UNIQUE_COUNT)
                .map(Entry::getKey)
                .findFirst();
    }

    private List<Player> generatePlayers(List<String> playerNames) {
        return playerNames.stream()
                .map(Player::new)
                .toList();
    }

    public void giveCardToPlayer(int playerIndex, Card card) {
        Player player = findPlayerByIndex(playerIndex);
        player.add(card);
    }

    public Player findPlayerByIndex(int index) {
        if (isOutOfRange(index)) {
            throw new IllegalArgumentException("인덱스가 범위를 벗어났습니다.");
        }
        return this.players.get(index);
    }

    private boolean isOutOfRange(int index) {
        return index < 0 || players.size() <= index;
    }

    public int count() {
        return this.players.size();
    }

    public List<String> getPlayerNames() {
        return players.stream()
                .map(Player::getName)
                .toList();
    }
}
