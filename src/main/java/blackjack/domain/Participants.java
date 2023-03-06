package blackjack.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Participants {
    private final List<Participant> participants;

    private Participants(List<Participant> participants) {
        this.participants = participants;
    }

    public static Participants from(List<String> names) {
        List<Participant> players = new ArrayList<>();
        players.add(new Dealer());
        players.addAll(getParticipantsByNames(stripNames(names)));
        validateDuplicate(players);
        return new Participants(players);
    }

    private static List<String> stripNames(List<String> names) {
        return names.stream()
                    .map(String::strip)
                    .collect(Collectors.toList());
    }

    private static List<Participant> getParticipantsByNames(List<String> names) {
        return names.stream()
                    .map(Player::new)
                    .collect(Collectors.toList());
    }

    private static void validateDuplicate(List<Participant> participants) {
        if (new HashSet<>(participants).size() != participants.size()) {
            throw new IllegalArgumentException("중복인 이름은 입력하실 수 없습니다.");
        }
    }

    public List<Player> getPlayers() {
        return participants.stream()
                           .filter(participant -> participant instanceof Player)
                           .map(participant -> (Player) participant)
                           .collect(Collectors.toList());
    }

    public Dealer getDealer() {
        return (Dealer) participants.stream()
                                    .filter(participant -> participant instanceof Dealer)
                                    .findAny()
                                    .orElseThrow(() ->
                                            new IllegalArgumentException("딜러를 찾지 못했습니다."));
    }

    public List<String> getPlayerNames() {
        return getPlayers().stream()
                           .map(Participant::getName)
                           .collect(Collectors.toList());
    }

    public List<Participant> toList() {
        return List.copyOf(participants);
    }
}
