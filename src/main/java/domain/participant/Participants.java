package domain.participant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Participants {
    private static final int MIN_PLAYER_COUNT = 1;
    private static final int MAX_PLAYER_COUNT = 6;
    private static final int DEALER_POSITION = 0;

    private final List<Participant> participants;

    public Participants(List<Player> players) {
        validatePlayerCount(players);
        duplicatedPlayerName(players);
        this.participants = new ArrayList<>();
        participants.add(DEALER_POSITION, new Dealer());
        participants.addAll(players);
    }

    private void validatePlayerCount(List<Player> players) {
        int playerCount = players.size();
        if (playerCount < MIN_PLAYER_COUNT || MAX_PLAYER_COUNT < playerCount) {
            throw new IllegalArgumentException(
                    String.format("플레이어는 %d ~ %d명이어야 합니다.", MIN_PLAYER_COUNT, MAX_PLAYER_COUNT)
            );
        }
    }

    private void duplicatedPlayerName(List<Player> players) {
        Set<Player> uniquePlayers = new HashSet<>(players);
        if (players.size() != uniquePlayers.size()) {
            throw new IllegalArgumentException("중복된 플레이어가 존재합니다.");
        }
    }

    public List<Participant> getParticipants() {
        return Collections.unmodifiableList(participants);
    }

    public Dealer getDealer() {
        return (Dealer) participants.get(DEALER_POSITION);
    }

    public List<Player> getPlayers() {
        List<Participant> copiedParticipants = new ArrayList<>(participants);
        copiedParticipants.remove(DEALER_POSITION);

        return copiedParticipants.stream()
                .map(player -> (Player) player)
                .toList();
    }

    public List<String> getPlayerNames() {
        return getPlayers().stream()
                .map(Player::getName)
                .toList();
    }
}
