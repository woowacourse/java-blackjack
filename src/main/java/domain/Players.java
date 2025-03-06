package domain;

import java.util.List;

public class Players {
    private static final int MAX_SIZE = 6;

    private final List<Player> players;

    public Players(List<Player> players) {
        validateSize(players);
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

    public int size() {
        return players.size();
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Dealer getDealer() {
        return (Dealer) players.stream()
                .filter(player -> player instanceof Dealer)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("딜러는 항상 1명이 존재해야 합니다."));
    }

    public List<Participant> getParticipants() {
        return  players.stream()
                .filter(player -> player instanceof Participant)
                .map(participant -> (Participant)participant)
                .toList();
    }
}
