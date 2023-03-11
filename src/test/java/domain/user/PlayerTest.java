package domain.user;

import org.junit.jupiter.api.Test;

import static domain.Fixtures.SPADE_ACE;
import static domain.Fixtures.SPADE_TWO;

public class PlayerTest {

    @Test
    void test() {
        Player player = new Player("pobi");

        player.hit(SPADE_ACE);
        player.hit(SPADE_TWO);

    }
}
