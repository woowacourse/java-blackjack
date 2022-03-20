package blackjack.domain.player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Participants {

    private static final Name DEALER_NAME = new Name("딜러");

    private final List<Participant> participants;

    public Participants(List<Participant> participants) {
        this.participants = participants;
    }

    public Participant findParticipant(Name name) {
        return participants.stream()
            .filter(participant -> participant.equals(name))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("해당 플레이어의 이름을 찾지 못했습니다."));
    }

    public Dealer findDealer() {
        return (Dealer)findParticipant(DEALER_NAME);
    }

    public List<Participant> getParticipants() {
        return new ArrayList<>(participants);
    }

    public List<Player> getPlayers() {
        return participants.stream()
            .filter(participant -> participant instanceof Player)
            .map(participant -> (Player)participant)
            .collect(Collectors.toList());
    }
}
