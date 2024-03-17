package fixture;

import domain.participant.Player;
import domain.participant.betting.BetAmount;
import domain.participant.name.Name;

public class ParticipantFixture {
    public static Player 플레이어(String name) {
        return 플레이어(name, 10);
    }

    public static Player 플레이어(String name, int betAmount) {
        return new Player(new Name(name), new BetAmount(betAmount));
    }
}
