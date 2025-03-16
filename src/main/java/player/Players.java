package player;

import card.Card;
import card.Deck;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Players {
    private static final int MAX_SIZE = 6;

    private final List<Player> players;

    public Players(List<Player> players) {
        validateSize(players);
        validateUniqueNames(players);
        this.players = players;
    }

    private void validateSize(List<Player> players) {
        if (players.size() < 2 || players.size() > MAX_SIZE) {
            throw new IllegalArgumentException("참여자 수는 딜러 포함 최소 2인 이상 최대 6인 이하여야 합니다.");
        }
    }

    public void distributeInitialCards(Deck deck) {
        for (Player player : players) {
            player.receiveInitialCards(deck);
        }
    }

    public Map<String, List<Card>> openInitialCards() {
        Map<String, List<Card>> initialCards = new LinkedHashMap<>();
        players.forEach(player -> initialCards.put(player.getName(), player.openInitialCards()));

        return initialCards;
    }

    public int size() {
        return players.size();
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Player getPlayerByName(String name) {
        return players.stream()
                .filter(player -> Objects.equals(player.getName(), name))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(name + ": 존재하지 않는 플레이어 이름입니다."));
    }

    public Dealer getDealer() {
        return (Dealer) players.stream()
                .filter(player -> player instanceof Dealer)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("딜러는 항상 1명이 존재해야 합니다."));
    }

    public List<Player> getParticipants() {
        return players.stream()
                .filter(player -> player instanceof Participant)
                .toList();
    }

    public Map<String, Integer> mapToNameAndSum() {
        Map<String, Integer> sumResult = new LinkedHashMap<>();
        for (Player player : players) {
            sumResult.put(player.getName(), player.computeOptimalSum());
        }
        return sumResult;
    }

    public void validateUniqueNames(List<Player> players) {
        Set<String> names = players.stream()
                .map(Player::getName)
                .collect(Collectors.toSet());

        if (names.size() != players.size()) {
            throw new IllegalArgumentException("플레이어 이름은 중복될 수 없습니다.");
        }
    }
}
