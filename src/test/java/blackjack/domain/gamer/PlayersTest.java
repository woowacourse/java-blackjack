package blackjack.domain.gamer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PlayersTest {
    @Test
    @DisplayName("플레이어는 List<String>으로 생성")
    void split() {
        List<String> names = Arrays.asList("pobi", "jun", "woni");
        Players players = Players.from(names);

        assertThat(players).isInstanceOf(Players.class);
    }
}