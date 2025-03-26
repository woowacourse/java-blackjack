package domain.fixture;

import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Participants;
import domain.participant.Player;
import java.util.ArrayList;
import java.util.List;

public class ParticipantsFixture {
    public static Participants createParticipants(List<String> playerNames) {
        List<Participant> participants = new ArrayList<>();
        participants.add(new Dealer());
        playerNames.forEach(name -> participants.add(new Player(name)));
        return new Participants(participants);
    }
}
