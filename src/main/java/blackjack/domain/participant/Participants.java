package blackjack.domain.participant;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Participants {
    private static final int MAX_PLAYER_COUNT = 10;

    private final List<Player> players;
    private final Dealer dealer;

    private Participants(List<Player> players) {
        this.players = players;
        this.dealer = new Dealer();
    }

    public static Participants from(List<String> names) {
        validatePlayerCount(names);
        List<Player> players = getParticipantsByNames(stripNames(names));
        validateDuplicate(players);
        return new Participants(players);
    }

    private static void validatePlayerCount(List<String> names) {
        if (names.size() > MAX_PLAYER_COUNT) {
            throw new IllegalArgumentException("게임 참여자는 10명까지 입력 가능합니다.");
        }
    }

    private static List<Player> getParticipantsByNames(List<String> names) {
        return names.stream()
                    .map(Player::from)
                    .collect(Collectors.toList());
    }

    private static List<String> stripNames(List<String> names) {
        return names.stream()
                    .map(String::strip)
                    .collect(Collectors.toList());
    }

    private static void validateDuplicate(List<Player> participants) {
        if (new HashSet<>(participants).size() != participants.size()) {
            throw new IllegalArgumentException("중복인 이름은 입력하실 수 없습니다.");
        }
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
