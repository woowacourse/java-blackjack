package domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
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
            // given
            List<TrumpCard> nullCards = null;

            // when & then
            assertThatThrownBy(() -> new Deck(nullCards)).isInstanceOf(IllegalArgumentException.class);
        }

        @DisplayName("덱의 카드는 52장 이어야한다.")
        @Test
        void validateSize() {
            // given
            List<TrumpCard> cards = new ArrayList<>();

            // when
            cards.add(TrumpCard.ACE_OF_CLUBS);

            // then
            assertThatThrownBy(() -> new Deck(cards)).isInstanceOf(IllegalArgumentException.class);
        }
    }
}
