package fixture;

import blackjack.domain.player.Participant;
import blackjack.domain.player.PlayerName;

public class PlayerFixture {

    public static Participant of(String name, int... hand) {
        return new Participant(new PlayerName(name), HandFixture.of(hand));
    }
}
