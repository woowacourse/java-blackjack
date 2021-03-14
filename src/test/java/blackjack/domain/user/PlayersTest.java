package blackjack.domain.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayersTest {
    @DisplayName("Players 객체를 생성한다.")
    @Test
    public void createPlayers() {
        Players players = new Players(Arrays.asList(
                new Player("amazzi", 10000.0),
                new Player("dani", 10000.0),
                new Player("pobi", 20000.0)));

        assertThat(players).isInstanceOf(Players.class);
    }
}
