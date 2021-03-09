package blackjack.domain.gamer;

import blackjack.domain.money.Money;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;


public class PlayersTest {
    @Test
    void getNames() {
        Players players = Players.of(Arrays.asList("joanne", "pk"),
                Arrays.asList(new Money("1000"), new Money("1000")));
        assertThat(players.getNames()).contains("joanne", "pk");
    }
}
