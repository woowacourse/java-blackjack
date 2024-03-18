package blackjack.fixture;

import blackjack.domain.participant.BattingAmount;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;

import java.util.Arrays;
import java.util.stream.Collectors;

public class PlayerFixture {

    public static Player 플레이어(String name) {
        return new Player(name, new BattingAmount(10000));
    }

    public static Players 플레이어들(String... names) {
        return Arrays.stream(names)
                .map(PlayerFixture::플레이어)
                .collect(Collectors.collectingAndThen(Collectors.toList(), Players::new));
    }

    public static Players 플레이어들(Player... players) {
        return new Players(Arrays.asList(players));
    }
}
