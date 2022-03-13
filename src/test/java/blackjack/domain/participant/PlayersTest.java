package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @Test
    void test() {
        Players players = new Players(List.of("eden"));
        assertThat(players.getPlayers().size()).isEqualTo(2);
    }
}
