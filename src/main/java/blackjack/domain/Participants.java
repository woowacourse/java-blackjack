package blackjack.domain;

import static java.util.stream.Collectors.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Participants {
    private final List<Participant> participants = new ArrayList<>();

    public Participants(Dealer dealer, List<Player> players) {
        validateDuplicatePlayer(players);
        this.participants.add(dealer);
        this.participants.addAll(players);
    }

    private void validateDuplicatePlayer(List<Player> players) {
        long uniquePlayersCount = players.stream()
                .map(Player::getName)
                .distinct()
                .count();
        if (uniquePlayersCount != players.size()) {
            throw new IllegalArgumentException("[ERROR] 중복된 이름이 있습니다.");
        }
    }

    public List<Player> getPlayers() {
        return participants.stream()
                .filter(Participant::isPlayer)
                .map(Player.class::cast)
                .collect(toList());
    }

    public Dealer getDealer() {
        return (Dealer) participants.stream()
                .filter(Participant::isDealer)
                .findAny()
                .orElseThrow(() -> new IllegalStateException("[ERROR] 딜러가 없습니다."));
    }

    public List<Participant> getParticipants() {
        return Collections.unmodifiableList(participants);
    }
}
