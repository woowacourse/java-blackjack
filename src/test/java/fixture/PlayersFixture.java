package fixture;

import domain.Player;
import domain.Players;
import java.util.Arrays;

public class PlayersFixture {
    public static Player 플레이어(String name) {
        return new Player(name);
    }

    public static Players 플레이어들(String... names) {
        return new Players(Arrays.asList(names));
    }
}
