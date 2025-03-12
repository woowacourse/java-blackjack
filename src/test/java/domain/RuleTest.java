package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class RuleTest {

    @Nested
    class ValidCases {

        @ParameterizedTest
        @DisplayName("플레이어가 히트를 할 수 있는 경우")
        @MethodSource("provideHitAllowedCases")
        void isPlayerHitAllowed(List<TrumpCard> cards) {
            // given
            Rule rule = new Rule();

            // when
            boolean result = rule.isPlayerHitAllowed(cards);

            // then
            assertThat(result).isTrue();
        }

        static Stream<Arguments> provideHitAllowedCases() {
            return Stream.of(
                    Arguments.of(List.of(TrumpCard.TWO_OF_DIAMONDS, TrumpCard.FIVE_OF_SPADES)),
                    Arguments.of(List.of(TrumpCard.TEN_OF_HEARTS)),
                    Arguments.of(List.of(TrumpCard.NINE_OF_CLUBS, TrumpCard.TWO_OF_DIAMONDS)),
                    Arguments.of(List.of(TrumpCard.FOUR_OF_HEARTS, TrumpCard.FIVE_OF_SPADES))
            );
        }

        @ParameterizedTest
        @DisplayName("플레이어가 히트를 할 수 없는 경우")
        @MethodSource("provideHitNotAllowedCases")
        void isPlayerHitNotAllowed(List<TrumpCard> cards) {
            // given
            Rule rule = new Rule();

            // when
            boolean result = rule.isPlayerHitAllowed(cards);

            // then
            assertThat(result).isFalse();
        }

        static Stream<Arguments> provideHitNotAllowedCases() {
            return Stream.of(
                    Arguments.of(List.of(TrumpCard.TEN_OF_SPADES, TrumpCard.ACE_OF_HEARTS)),
                    Arguments.of(
                            List.of(TrumpCard.KING_OF_DIAMONDS, TrumpCard.QUEEN_OF_SPADES, TrumpCard.TWO_OF_HEARTS)),
                    Arguments.of(List.of(TrumpCard.JACK_OF_CLUBS, TrumpCard.KING_OF_HEARTS, TrumpCard.FOUR_OF_SPADES))
            );
        }

        @ParameterizedTest
        @DisplayName("딜러가 히트를 할 수 있는 경우")
        @MethodSource("provideDealerHitAllowedCases")
        void isDealerHitAllowed(List<TrumpCard> cards) {
            // given
            Rule rule = new Rule();

            // when
            boolean result = rule.isDealerHitAllowed(cards);

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

        @ParameterizedTest
        @DisplayName("딜러가 히트를 할 수 없는 경우")
        @MethodSource("provideDealerHitNotAllowedCases")
        void isDealerHitNotAllowed(List<TrumpCard> cards) {
            // given
            Rule rule = new Rule();

            // when
            boolean result = rule.isDealerHitAllowed(cards);

            // then
            assertThat(result).isFalse();
        }

        static Stream<Arguments> provideDealerHitNotAllowedCases() {
            return Stream.of(
                    Arguments.of(List.of(TrumpCard.TEN_OF_SPADES, TrumpCard.SEVEN_OF_CLUBS)),
                    Arguments.of(List.of(TrumpCard.KING_OF_DIAMONDS, TrumpCard.QUEEN_OF_SPADES)),
                    Arguments.of(List.of(TrumpCard.JACK_OF_CLUBS, TrumpCard.KING_OF_HEARTS, TrumpCard.FOUR_OF_SPADES))
            );
        }

        @ParameterizedTest
        @DisplayName("카드 목록의 점수를 올바르게 평가한다")
        @MethodSource("provideScoreEvaluationCases")
        void evaluateScore(List<TrumpCard> cards, Score expectedScore) {
            // given
            Rule rule = new Rule();

            // when
            Score result = rule.evaluateScore(cards);

            // then
            assertThat(result).isEqualTo(expectedScore);
        }

        static Stream<Arguments> provideScoreEvaluationCases() {
            return Stream.of(
                    Arguments.of(List.of(TrumpCard.ACE_OF_SPADES, TrumpCard.KING_OF_HEARTS), Score.BLACKJACK),
                    Arguments.of(List.of(TrumpCard.NINE_OF_CLUBS, TrumpCard.SEVEN_OF_DIAMONDS), Score.SIXTEEN),
                    Arguments.of(List.of(TrumpCard.TEN_OF_DIAMONDS, TrumpCard.JACK_OF_HEARTS), Score.TWENTY),
                    Arguments.of(List.of(TrumpCard.FIVE_OF_SPADES, TrumpCard.THREE_OF_DIAMONDS), Score.EIGHT)
            );
        }

        @ParameterizedTest
        @DisplayName("플레이어와 딜러 점수를 비교하여 게임 결과를 평가한다")
        @MethodSource("provideGameResultEvaluationCases")
        void evaluateGameResult(List<TrumpCard> playerCards, List<TrumpCard> dealerCards, GameResult expectedResult) {
            // given
            Rule rule = new Rule();

            // when
            GameResult result = rule.evaluateGameResult(playerCards, dealerCards);

            // then
            assertThat(result).isEqualTo(expectedResult);
        }

        static Stream<Arguments> provideGameResultEvaluationCases() {
            return Stream.of(
                    Arguments.of(
                            List.of(TrumpCard.ACE_OF_SPADES, TrumpCard.KING_OF_HEARTS),
                            List.of(TrumpCard.NINE_OF_CLUBS, TrumpCard.SEVEN_OF_DIAMONDS),
                            GameResult.BLACKJACK_WIN
                    ),
                    Arguments.of(
                            List.of(TrumpCard.TEN_OF_DIAMONDS, TrumpCard.JACK_OF_HEARTS),
                            List.of(TrumpCard.TEN_OF_CLUBS, TrumpCard.QUEEN_OF_SPADES),
                            GameResult.DRAW
                    ),
                    Arguments.of(
                            List.of(TrumpCard.FIVE_OF_SPADES, TrumpCard.THREE_OF_DIAMONDS),
                            List.of(TrumpCard.TEN_OF_SPADES, TrumpCard.SEVEN_OF_HEARTS),
                            GameResult.LOSE
                    )
            );
        }
    }
}
