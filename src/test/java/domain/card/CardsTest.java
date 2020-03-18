package domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class CardsTest {

    @Test
    @DisplayName("#of() : should return cards with cards which size is bigger than MIN_SIZE")
    void ofValidSize() {
        List<Card> validCards = Collections.singletonList(new Card(Symbol.QUEEN, Type.CLOVER));
        assertThat(Cards.of(validCards)).isNotNull();
    }

    @Test
    @DisplayName("#of() : should throw IllegalArguemntException with cards which size is smaller than MIN_SIZE")
    void ofInvalidSize() {
        List<Card> invalidCards = Collections.emptyList();
        assertThatThrownBy(() -> Cards.of(invalidCards))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Cards.INVALID_SIZE_MESSAGE);
    }
}