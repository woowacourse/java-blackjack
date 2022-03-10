package domain.card;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Validate;

import static org.assertj.core.api.Assertions.assertThatNoException;

public class CardTest {
    @Test
    void create() {
        // then
        assertThatNoException().isThrownBy(() -> new Card(Denomination.ACE, Suit.CLOVER));
    }
}
