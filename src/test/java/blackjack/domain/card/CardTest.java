package blackjack.domain.card;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatNoException;

public class CardTest {
    @Test
    void create() {
        // then
        assertThatNoException().isThrownBy(() -> new Card(Number.ACE, Suit.CLOVER));
    }
}
