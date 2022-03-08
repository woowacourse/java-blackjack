package blackjack;

import blackjack.domain.Player;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    void name() {
        String name = "pobi";
        Player pobi = new Player(name);
        Assertions.assertThat(pobi.getName()).isEqualTo(name);
    }
}
