package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayersTest {
    @Test
    @DisplayName("플레이어는 여러명일 수 있다.")
    void playersCreateTest() {
        // given
        String names = "pobi,lemone,seyang";
        List<String> expectedNames = List.of("pobi", "lemone", "seyang");

        // when
        Players players = Players.from(names);

        // then
        assertThat(players.getNames())
                .usingRecursiveComparison()
                .isEqualTo(expectedNames);
    }
}
