package domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.AssertionsForClassTypes;
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

        @Test
        @DisplayName("딜러는 점수를 계산한다.")
        void calculateDealerScore() {
            // given
            List<TrumpCard> cards = List.of(TrumpCard.ACE_OF_SPADES, TrumpCard.KING_OF_HEARTS);
            Dealer dealer = new Dealer(new Hand(cards));
            Rule rule = new Rule();

            // when
            Score score = dealer.calculateScore(rule);

            // then
            Assertions.assertThat(score).isEqualTo(Score.BLACKJACK);
        }

        @DisplayName("딜러의 첫번째 카드를 가져온다.")
        @Test
        void retrieveFirstCard() {
            // given
            List<TrumpCard> cards = List.of(
                    TrumpCard.ACE_OF_SPADES,
                    TrumpCard.TWO_OF_SPADES
            );
            Hand hand = new Hand(cards);
            Dealer dealer = new Dealer(hand);

            // when
            TrumpCard dealerFirstCard = dealer.retrieveFirstCard();

            // then
            AssertionsForClassTypes.assertThat(dealerFirstCard).isEqualTo(TrumpCard.ACE_OF_SPADES);
        }

        @ParameterizedTest
        @DisplayName("딜러의 히트 판단 여부를 판단한다")
        @MethodSource("provideDealerHitAllowedCases")
        void isHitAllowed(List<TrumpCard> cards) {
            // given
            Dealer dealer = new Dealer(new Hand(cards));
            Rule rule = new Rule();

            // when
            boolean result = dealer.isHitAllowed(rule);

            // then
            assertThat(result).isTrue();
        }

        static Stream<Arguments> provideDealerHitAllowedCases() {
            return Stream.of(
                    Arguments.of(List.of(TrumpCard.FIVE_OF_CLUBS, TrumpCard.SIX_OF_HEARTS)),
                    Arguments.of(List.of(TrumpCard.SEVEN_OF_DIAMONDS, TrumpCard.TWO_OF_SPADES)),
                    Arguments.of(
                            List.of(TrumpCard.THREE_OF_HEARTS, TrumpCard.THREE_OF_DIAMONDS, TrumpCard.TWO_OF_SPADES))
            );
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

        @DisplayName("딜러의 첫번째 카드를 가져올 때 딜러는 초기 카드의 개수를 가지고 있어야 한다.")
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
                    .hasMessage("딜러는 " + BlackJackGame.INITIAL_CARD_COUNT + "장의 카드를 가지고 있어야 합니다.");
        }
    }
}
