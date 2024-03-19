package fixture;

import domain.participant.Player;

public class ParticipantFixture {
    public static Player 플레이어(String name) {
        return new Player(name, 10);
    }
}
