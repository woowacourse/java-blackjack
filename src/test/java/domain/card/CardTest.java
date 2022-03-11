package domain.card;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Validate;

import static org.assertj.core.api.Assertions.assertThatNoException;

public class CardTest {
    @Test
    @DisplayName("카드는 value object이다.")
    void create() {
        Card card = Card.valueOf(Denomination.ACE, Suit.CLOVER);
        Assertions.assertThat(card).isEqualTo(Card.valueOf(Denomination.ACE, Suit.CLOVER));
    }
}
