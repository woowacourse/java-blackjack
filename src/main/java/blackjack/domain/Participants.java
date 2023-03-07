package blackjack.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Participants {
    private final List<Player> players;
    private final Dealer dealer;

    private Participants(List<Player> players) {
        this.players = players;
        this.dealer = new Dealer();
    }

    public static Participants from(List<String> names) {
        List<Player> players = getParticipantsByNames(stripNames(names));
        validateDuplicate(players);
        return new Participants(players);
    }

    private static List<String> stripNames(List<String> names) {
        return names.stream()
                    .map(String::strip)
                    .collect(Collectors.toList());
    }

    private static List<Player> getParticipantsByNames(List<String> names) {
        return names.stream()
                    .map(Player::from)
                    .collect(Collectors.toList());
    }

    private static void validateDuplicate(List<Player> participants) {
        if (new HashSet<>(participants).size() != participants.size()) {
            throw new IllegalArgumentException("중복인 이름은 입력하실 수 없습니다.");
        }
    }

    public List<String> getPlayerNames() {
        return getPlayers().stream()
                           .map(Participant::getName)
                           .collect(Collectors.toList());
    }

    public List<Participant> getParticipants() {
        List<Participant> participant = new ArrayList<>(List.of(dealer));
        participant.addAll(players);
        return participant;
    }

    public List<Player> getPlayers() {
        return List.copyOf(players);
    }

    public Dealer getDealer() {
        return dealer;
    }
}
