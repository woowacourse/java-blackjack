package domain.card;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatNoException;

public class CardTest {
    @Test
    void create() {
        assertThatNoException().isThrownBy(() -> new Card(Denomination.ACE, Suit.CLOVER));
    }
}
