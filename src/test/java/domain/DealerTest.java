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

        @DisplayName("딜러의 손패에서 카드를 가져온다.")
        @Test
        void retrieveCards() {
            // given
            List<TrumpCard> cards = List.of(
                    TrumpCard.ACE_OF_SPADES,
                    TrumpCard.TWO_OF_SPADES
            );
            Hand hand = new Hand(cards);
            Dealer dealer = new Dealer(hand);

            // when
            List<TrumpCard> retrievedCards = dealer.retrieveCards();

            // then
            assertThat(retrievedCards).isEqualTo(cards);
        }

        @DisplayName("딜러는 카드를 받아 손패에 추가한다.")
        @Test
        void receiveCard() {
            // given
            List<TrumpCard> cards = List.of(
                    TrumpCard.ACE_OF_SPADES
            );
            Hand hand = new Hand(cards);
            Dealer dealer = new Dealer(hand);

            // when
            dealer.receiveCard(TrumpCard.TWO_OF_SPADES);

            // then
            assertThat(dealer.retrieveCards()).isEqualTo(
                    List.of(TrumpCard.ACE_OF_SPADES, TrumpCard.TWO_OF_SPADES));
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
                    .hasMessage("참가자는 손패를 가져야합니다.");
        }
    }
}
