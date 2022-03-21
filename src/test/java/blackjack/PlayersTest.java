package blackjack;

import static org.assertj.core.api.Assertions.*;

import blackjack.domain.Players;
import java.util.List;
import org.junit.jupiter.api.Test;

public class PlayersTest {

    @Test
    void noDuplicationNameTest() {
        assertThatThrownBy(() -> Players.generateWithNames(List.of("pobi", "pobi")))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
