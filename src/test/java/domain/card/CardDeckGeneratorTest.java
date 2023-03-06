package domain.card;

import static org.assertj.core.api.Assertions.assertThatNoException;

import org.junit.jupiter.api.Test;

class CardDeckGeneratorTest {

    @Test
    void create_success() {
        // when && then
        assertThatNoException().isThrownBy(() -> CardDeckGenerator.create());
    }
}
