package blackjack.domain.participant;

import static java.util.stream.Collectors.toUnmodifiableList;

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
                .collect(toUnmodifiableList());
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

    public Player getPlayerByName(Name playerName) {
        return (Player) participants.stream()
                .filter(participant -> participant.isSameName(playerName))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 해당 플레이어가 없습니다."));
    }
}
