package blackjack.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Participants {

    private final List<Participant> participants;

    private Participants(List<Participant> participants) {
        this.participants = participants;
    }

    public static Participants generate(List<String> playersName) {
        List<Participant> participants = new ArrayList<>();
        participants.add(new Dealer(new ParticipantName("딜러")));
        for (String playerName : playersName) {
            participants.add(new Player(new ParticipantName(playerName)));
        }
        return new Participants(participants);
    }

    public List<String> getParticipantsName() {
        return participants.stream()
            .map(participant -> participant.getParticipantName().getName())
            .collect(Collectors.toUnmodifiableList());
    }

    public List<Participant> getParticipants() {
        return participants;
    }

}
