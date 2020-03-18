package blackjack.domain.playing.user;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {
    @Test
    void of() {
        assertThat(Player.of("무늬")).isNotNull();
    }
}