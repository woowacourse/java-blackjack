package blackjack.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Participants {

    private final List<Participant> participants;

    private Participants(final List<Participant> participants) {
        this.participants = participants;
    }

    public static Participants from(final List<String> playersName,final Dealer dealer) {
        List<Participant> participants = new ArrayList<>();
        participants.add(dealer);
        for (String playerName : playersName) {
            participants.add(new Player(new ParticipantName(playerName)));
        }
        return new Participants(participants);
    }

    public List<String> getParticipantsName() {
        return participants.stream()
            .map(participant -> participant.getParticipantName().getName())
            .collect(Collectors.toList());
    }

    public List<Participant> getParticipants() {
        return participants;
    }
}
