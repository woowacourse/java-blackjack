package blackjack.domain.participant;

import blackjack.domain.card.Card;

import java.util.HashSet;
import java.util.List;

public class Players {
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
        if (playerNames.size() != new HashSet<>(playerNames).size()) {
            throw new IllegalArgumentException("중복된 이름이 존재합니다.");
        }
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
