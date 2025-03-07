package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class HandTest {

    @Nested
    class ValidCases {

        @DisplayName("손패는 카드를 추가할 수 있다")
        @Test
        void addCard() {
            // given
            List<TrumpCard> cards = new ArrayList<>();
            cards.add(TrumpCard.ACE_OF_SPADES);
            cards.add(TrumpCard.TWO_OF_SPADES);
            Hand hand = new Hand(cards);

            // when
            hand.addCard(TrumpCard.THREE_OF_SPADES);

            // then
            assertThat(hand.getCards()).containsExactlyInAnyOrder(
                    TrumpCard.ACE_OF_SPADES,
                    TrumpCard.TWO_OF_SPADES,
                    TrumpCard.THREE_OF_SPADES
            );
        }
    }

    @Nested
    class InvalidCases {

        @DisplayName("손패는 카드가 있어야 한다")
        @Test
        void validateNotNull() {
            // given
            List<TrumpCard> nullCards = null;

            // when & then
            assertThatThrownBy(() -> new Hand(nullCards))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("손패는 카드를 가지고 있어야합니다.");
        }

    }
}
