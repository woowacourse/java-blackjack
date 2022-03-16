package blackjack.domain.player;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import blackjack.domain.BetMoney;
import org.junit.jupiter.api.Test;

class GamblerTest {

    @Test
    void create() {
        assertDoesNotThrow( () -> new Gambler("돌범", new BetMoney(1000)));
    }
}
