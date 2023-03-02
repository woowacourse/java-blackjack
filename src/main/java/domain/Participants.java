package domain;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Participants {
    private final List<Participant> participants;

    private Participants(List<Participant> participants) {
        this.participants = participants;
    }

    public static Participants from(List<String> names) {
        validateDuplicate(names);
        List<Participant> players = names.stream()
                                         .map(Player::new)
                                         .collect(Collectors.toList());
        players.add(new Dealer());
        return new Participants(players);
    }

    private static void validateDuplicate(List<String> names) {
        if (new HashSet<>(names).size() != names.size()) {
            throw new IllegalArgumentException("중복인 이름은 입력하실 수 없습니다.");
        }
    }

    public List<Participant> getPlayers() {
        return participants.stream()
                           .filter(participant -> participant instanceof Player)
                           .collect(Collectors.toList());
    }

    public Participant getDealer() {
        return participants.stream()
                           .filter(participant -> participant instanceof Dealer)
                           .findAny()
                           .orElseThrow(() -> new IllegalArgumentException("딜러를 " +
                                   "찾지 못했습니다."));
    }

    public List<Participant> toList() {
        return List.copyOf(participants);
    }
}
