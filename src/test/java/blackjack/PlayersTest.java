package blackjack;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayersTest {

    @Test
    void noDuplicationNameTest() {
        assertThatThrownBy(() -> Players.generateWithNames(List.of("pobi", "pobi")))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void turnPlayerTest() {
        Players players = Players.generateWithNames(List.of("pobi", "jason"));
        assertThat(players.turnPlayer().getName()).isEqualTo("pobi");
    }

    @Test
    void changeTurnTest() {
        Players players = Players.generateWithNames(List.of("pobi", "jason"));
        players.changeTurn();
        assertThat(players.turnPlayer().getName()).isEqualTo("jason");
    }
}
