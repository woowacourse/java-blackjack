package domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Players {

    private static final int MIN_SIZE = 2;
    private static final int MAX_SIZE = 6;

    private final List<Player> players;

    public Players(List<Player> players) {
        validateSize(players);
        validateUniqueNames(players);
        this.players = players;
    }

    private void validateSize(List<Player> players) {
        if (players.size() < MIN_SIZE || players.size() > MAX_SIZE) {
            throw new IllegalArgumentException("참여자 수는 딜러 포함 최소 2인 이상 최대 6인 이하여야 합니다.");
        }
    }

    private void validateUniqueNames(List<Player> players) {
        if (players.stream()
                .map(Player::getName)
                .distinct()
                .count() != players.size()) {
            throw new IllegalArgumentException("플레이어 이름은 중복될 수 없습니다.");
        }
    }

    public void distributeInitialCards(Deck deck) {
        for (Player player : players) {
            player.receiveInitialCards(deck);
        }
    }

    public Map<String, Integer> mapToNameAndSum() {
        Map<String, Integer> sumResult = new HashMap<>();
        for (Player player : players) {
            sumResult.put(player.getName(), player.computeOptimalSum());
        }
        return sumResult;
    }

    public int size() {
        return players.size();
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Player getPlayer(Player player) {
        return players.stream()
                .filter(p -> p == player)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(player + ": 존재하지 않는 플레이어입니다."));
    }

    public Dealer getDealer() {
        return (Dealer) players.stream()
                .filter(Player::isDealer)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("딜러는 항상 1명이 존재해야 합니다."));
    }

    public List<Player> getParticipants() {
        return players.stream()
                .filter(player -> !player.isDealer())
                .toList();
    }
}
