package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

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

    public List<Participant> getParticipants() {
        return participants;
    }

}
