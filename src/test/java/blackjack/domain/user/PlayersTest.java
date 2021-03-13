package blackjack.domain.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayersTest {
    @DisplayName("Players 객체를 생성한다.")
    @Test
    public void createPlayers() {
        Players players = new Players(Arrays.asList("amazzi", "dani", "pobi"),
                Arrays.asList(10000.0, 10000.0, 20000.0));

        assertThat(players).isInstanceOf(Players.class);
    }
}
