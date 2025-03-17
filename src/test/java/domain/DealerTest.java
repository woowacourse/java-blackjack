package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

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
            State state = Started.of(new Hand(cards), Score.SEVENTEEN);
            Dealer dealer = new Dealer(state);

            // when
            List<TrumpCard> retrievedCards = dealer.retrieveCards();

            // then
            assertThat(retrievedCards).isEqualTo(cards);
        }

        @DisplayName("딜러는 카드를 받아 손패에 추가한다.")
        @Test
        void receiveCard() {
            // given
            State state = Started.of(new Hand(List.of(
                            TrumpCard.FIVE_OF_CLUBS, TrumpCard.JACK_OF_CLUBS)),
                    Score.SEVENTEEN);
            Dealer dealer = new Dealer(state);

            // when
            dealer.receiveCard(TrumpCard.TWO_OF_SPADES);

            // then
            assertThat(dealer.retrieveCards().size()).isEqualTo(3);
        }

        @Test
        @DisplayName("딜러는 점수를 계산한다.")
        void calculateDealerScore() {
            // given
            List<TrumpCard> cards = List.of(TrumpCard.ACE_OF_SPADES, TrumpCard.KING_OF_HEARTS);
            State state = Started.of(new Hand(cards), Score.SEVENTEEN);
            Dealer dealer = new Dealer(state);

            // when
            Score score = dealer.calculateScore();

            // then
            assertThat(score).isEqualTo(Score.BLACKJACK);
        }

        @DisplayName("딜러의 첫번째 카드를 가져온다.")
        @Test
        void retrieveFirstCard() {
            // given
            List<TrumpCard> cards = List.of(
                    TrumpCard.ACE_OF_SPADES,
                    TrumpCard.TWO_OF_SPADES
            );
            State state = Started.of(new Hand(cards), Score.SEVENTEEN);
            Dealer dealer = new Dealer(state);

            // when
            TrumpCard dealerFirstCard = dealer.retrieveFirstCard();

            // then
            assertThat(dealerFirstCard).isEqualTo(TrumpCard.ACE_OF_SPADES);
        }

        @ParameterizedTest
        @DisplayName("딜러의 히트 가능 여부를 확인한다")
        @MethodSource("provideDealerHitAllowedCases")
        void isHitAllowed(List<TrumpCard> cards, Score limitScore, boolean expected) {
            // given
            State state = Started.of(new Hand(cards), limitScore);
            Dealer dealer = new Dealer(state);

            // when
            boolean result = dealer.isHitAllowed();

            // then
            assertThat(result).isEqualTo(expected);
        }

        static Stream<Arguments> provideDealerHitAllowedCases() {
            return Stream.of(
                    Arguments.of(List.of(TrumpCard.FIVE_OF_CLUBS, TrumpCard.SIX_OF_HEARTS), Score.SEVENTEEN, true),
                    Arguments.of(List.of(TrumpCard.SEVEN_OF_DIAMONDS, TrumpCard.TWO_OF_SPADES), Score.SEVENTEEN, true),
                    Arguments.of(List.of(TrumpCard.TEN_OF_SPADES, TrumpCard.KING_OF_HEARTS), Score.SEVENTEEN, false)
            );
        }
    }

    @Nested
    class InvalidCases {

        @DisplayName("딜러는 상태를 가져야 한다.")
        @Test
        void validateNotNull() {
            // given
            State nullState = null;

            // when & then
            assertThatThrownBy(() -> new Dealer(nullState))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("참가자는 상태를 가져야합니다.");
        }

        @DisplayName("딜러의 첫번째 카드를 가져올 때 초기 카드의 개수를 가지고 있어야 한다.")
        @Test
        void retrieveFirstCard() {
            // given
            List<TrumpCard> cards = List.of(
                    TrumpCard.TEN_OF_DIAMONDS, TrumpCard.JACK_OF_CLUBS, TrumpCard.THREE_OF_SPADES
            );
            State state = new Bust(new Hand(cards));
            Dealer dealer = new Dealer(state);

            // when & then
            assertThatThrownBy(dealer::retrieveFirstCard)
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage("딜러는 " + Started.INITIAL_CARD_COUNT + "장의 카드를 가지고 있어야 합니다.");
        }
    }
}
