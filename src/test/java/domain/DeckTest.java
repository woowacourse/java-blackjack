package domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class DeckTest {

    @Nested
    class InvalidCases {

        @DisplayName("덱은 카드를 가지고 있어야한다.")
        @Test
        void validateNotNull() {
            List<TrumpCard> nullCards = null;
            assertThatThrownBy(() -> new Deck(nullCards)).isInstanceOf(IllegalArgumentException.class);
        }
    }
}
