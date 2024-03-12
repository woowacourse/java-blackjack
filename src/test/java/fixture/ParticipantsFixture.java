package fixture;

import blackjack.domain.player.Participants;
import java.util.List;

public class ParticipantsFixture {

    public static Participants create() {
        List<String> playerNames = List.of("pobi", "jason");
        return Participants.create(playerNames);
    }
}
