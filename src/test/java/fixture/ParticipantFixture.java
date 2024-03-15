package fixture;

import domain.participant.Participants;
import domain.participant.Player;
import java.util.Arrays;

public class ParticipantFixture {
    public static Player 플레이어(String name) {
        return new Player(name);
    }

    public static Participants 참가자들(String... playerNames) {
        return Participants.createPlayers(Arrays.asList(playerNames));
    }
}
