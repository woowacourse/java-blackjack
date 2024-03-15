package domain.participant;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

public class Participants {
    private static final int MIN_PLAYER_COUNT = 1;
    private static final int MAX_PLAYER_COUNT = 6;

    private final Dealer dealer;
    private final List<Player> players;

    private Participants(List<Player> players) {
        this.dealer = new Dealer();
        this.players = players;
    }

    public static Participants createPlayers(List<String> playerNames) {
        validatePlayerCount(playerNames);
        validateDuplicatedPlayerName(playerNames);
        return playerNames.stream()
                .map(Player::new)
                .collect(collectingAndThen(toList(), Participants::new));
    }

    private static void validatePlayerCount(List<String> playerNames) {
        int playerCount = playerNames.size();
        if (playerCount < MIN_PLAYER_COUNT || MAX_PLAYER_COUNT < playerCount) {
            throw new IllegalArgumentException(
                    String.format("플레이어의 수는 %d ~ %d명이어야 합니다.", MIN_PLAYER_COUNT, MAX_PLAYER_COUNT)
            );
        }
    }

    private static void validateDuplicatedPlayerName(List<String> playerNames) {
        Map<String, Long> nameCounts = getPlayerNameCounts(playerNames);
        Optional<String> duplicatedName = findDuplicatedName(nameCounts);
        if (duplicatedName.isPresent()) {
            throw new IllegalArgumentException(
                    String.format("rejected value: %s - 중복된 이름이 존재합니다.", duplicatedName.get())
            );
        }
    }

    private static Map<String, Long> getPlayerNameCounts(List<String> playerNames) {
        return playerNames.stream()
                .collect(groupingBy(name -> name, counting()));
    }

    private static Optional<String> findDuplicatedName(Map<String, Long> nameCounts) {
        return nameCounts.entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 1)
                .map(Entry::getKey)
                .findFirst();
    }

    public List<Participant> getParticipants() {
        List<Participant> participants = new ArrayList<>();
        participants.add(dealer);
        participants.addAll(players);
        return Collections.unmodifiableList(participants);
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(this.players);
    }

    public List<String> getPlayerNames() {
        return players.stream()
                .map(Player::getName)
                .collect(toList());
    }
}
