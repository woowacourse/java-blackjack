package blackjack;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class PlayerTest {
    @Test
    void create() {
        assertThat(Player.of("포비")).isNotNull();
    }

    // addTest
}