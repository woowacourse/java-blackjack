package fixture;

import domain.Players;
import java.util.Arrays;

public class PlayersFixture {
    public static Players 플레이어들(String... names) {
        return new Players(Arrays.asList(names));
    }
}
