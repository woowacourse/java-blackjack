package domain.fixture;

import domain.Dealer;
import domain.Participant;
import domain.Participants;
import domain.Player;
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
