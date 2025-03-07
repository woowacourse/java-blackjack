package domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Nested
    class ValidCases {

        @DisplayName("딜러의 첫번째 카드를 가져온다.")
        @Test
        void retrieveFirstCard() {
            // given
            List<TrumpCard> cards = List.of(
                    TrumpCard.ACE_OF_SPADES,
                    TrumpCard.TWO_OF_SPADES
            );
            Hand hand = new Hand(cards);

            // when
            Dealer dealer = new Dealer(hand);

            // then
            assertThat(dealer.retrieveFirstCard()).isEqualTo(TrumpCard.ACE_OF_SPADES);
        }
    }

    @Nested
    class InvalidCases {

        @DisplayName("딜러는 손패를 가져야 한다.")
        @Test
        void validateNotNull() {
            // given
            Hand nullHand = null;

            // when & then
            assertThatThrownBy(() -> new Dealer(nullHand))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("딜러는 손패를 가져야합니다.");
        }

        @DisplayName("딜러의 첫번째 카드를 가져올 때 딜러는 2장의 카드를 가지고 있어야 한다.")
        @Test
        void retrieveFirstCard() {
            // given
            List<TrumpCard> cards = List.of(
                    TrumpCard.ACE_OF_SPADES
            );
            Hand hand = new Hand(cards);
            Dealer dealer = new Dealer(hand);

            // when & then
            assertThatThrownBy(dealer::retrieveFirstCard)
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage("딜러는 2장의 카드를 가지고 있어야 합니다.");
        }
    }
}
