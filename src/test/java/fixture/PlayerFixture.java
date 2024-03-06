package fixture;

import blackjack.domain.Player;
import blackjack.domain.PlayerName;

public class PlayerFixture {

    public static Player of(String name, int... hand) {
        return new Player(new PlayerName(name), HandFixture.of(hand));
    }
}
