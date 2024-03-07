package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @DisplayName("이름을 보고 플레이어를 찾는다.")
    @Test
    void findPlayerByName() {
        final Players players = Players.from(List.of(
                "pobi",
                "jason"));

        final Player actual = players.findByName(new Name("pobi"));

        assertThat(actual.getName()).isEqualTo(new Name("pobi"));
    }
}
