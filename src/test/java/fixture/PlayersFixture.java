package fixture;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

import domain.Participant;
import domain.Player;
import domain.Players;
import java.util.Arrays;

public class PlayersFixture {
    public static Players 플레이어들(String... names) {
        return new Players(Arrays.asList(names));
    }

    public static Players 플레이어들(Player... players) {
        return Arrays.stream(players)
                .map(Participant::getName)
                .collect(collectingAndThen(toList(), Players::new));
    }
}
