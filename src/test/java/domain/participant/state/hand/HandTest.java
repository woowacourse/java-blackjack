package domain.participant.state.hand;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.TrumpCard;
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

        @DisplayName("손패에 중복된 카드가 있으면 안된다")
        @Test
        void validateNotDuplicate() {
            // given
            List<TrumpCard> cards = List.of(
                    TrumpCard.ACE_OF_SPADES,
                    TrumpCard.ACE_OF_SPADES
            );

            // when & then
            assertThatThrownBy(() -> new Hand(cards))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("손패에 중복된 카드가 있습니다.");
        }

        @DisplayName("손패와 새로 받은 카드는 중복될 수 없다")
        @Test
        void validateAddDuplicateCard() {
            // given
            Hand hand = new Hand(List.of(TrumpCard.ACE_OF_SPADES, TrumpCard.TWO_OF_SPADES));

            // when & then
            assertThatThrownBy(() -> hand.addCard(TrumpCard.ACE_OF_SPADES))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("이미 손패에 있는 카드입니다.");
        }
    }
}
