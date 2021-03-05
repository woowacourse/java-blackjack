package blackjack.domain.gamer;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;


public class PlayersTest {
    @Test
    void getNames() {
        Players players = Players.of(Arrays.asList("joanne", "pk"));
        assertThat(players.getNames()).contains("joanne", "pk");
    }
}
