package fixture;

import blackjack.domain.participant.Participants;
import java.util.List;

public class ParticipantsFixture {

    public static Participants create() {
        List<String> playerNames = List.of("pobi", "jason");
        return Participants.create(playerNames);
    }
}
