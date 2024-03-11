package blackjack.fixture;

import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;

import java.util.Arrays;

public class PlayersFixture {
    public static Player 플레이어(String name) {
        return new Player(name);
    }

    public static Players 플레이어들(String... names) {
        return new Players(Arrays.asList(names));
    }
}
