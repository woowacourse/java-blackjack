package blackjack.domain.gamer;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @DisplayName("플레이어의 이름을 보고 해당 플레이어를 찾는다.")
    @Test
    void findPlayerByName() {
        final Players players = Players.from(List.of("pobi", "jason"));

        final Player foundPlayer = players.findBy("pobi");

        assertThat(foundPlayer.getName().value()).isEqualTo("pobi");
    }
}
