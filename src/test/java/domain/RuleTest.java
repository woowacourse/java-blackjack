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
                    Arguments.of(List.of(new TrumpCard(Rank.TWO, Suit.DIAMONDS), new TrumpCard(Rank.FIVE, Suit.SPADES))),
                    Arguments.of(List.of(new TrumpCard(Rank.TEN, Suit.HEARTS))),
                    Arguments.of(List.of(new TrumpCard(Rank.NINE, Suit.CLUBS), new TrumpCard(Rank.TWO, Suit.DIAMONDS))),
                    Arguments.of(List.of(new TrumpCard(Rank.FOUR, Suit.HEARTS), new TrumpCard(Rank.FIVE, Suit.SPADES)))
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
                    Arguments.of(List.of(new TrumpCard(Rank.TEN, Suit.SPADES), new TrumpCard(Rank.ACE, Suit.HEARTS))),
                    Arguments.of(List.of(new TrumpCard(Rank.KING, Suit.DIAMONDS), new TrumpCard(Rank.QUEEN, Suit.SPADES), new TrumpCard(Rank.TWO, Suit.SPADES))),
                    Arguments.of(List.of(new TrumpCard(Rank.JACK, Suit.CLUBS), new TrumpCard(Rank.KING, Suit.DIAMONDS), new TrumpCard(Rank.FOUR, Suit.HEARTS)))
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
                    Arguments.of(List.of(new TrumpCard(Rank.FIVE, Suit.CLUBS), new TrumpCard(Rank.SIX, Suit.HEARTS))),
                    Arguments.of(List.of(new TrumpCard(Rank.SEVEN, Suit.DIAMONDS), new TrumpCard(Rank.TWO, Suit.SPADES))),
                    Arguments.of(List.of(new TrumpCard(Rank.THREE, Suit.HEARTS), new TrumpCard(Rank.THREE, Suit.DIAMONDS), new TrumpCard(Rank.TWO, Suit.SPADES)))
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
                    Arguments.of(List.of(new TrumpCard(Rank.TEN, Suit.SPADES), new TrumpCard(Rank.SEVEN, Suit.CLUBS))),
                    Arguments.of(List.of(new TrumpCard(Rank.KING, Suit.DIAMONDS), new TrumpCard(Rank.QUEEN, Suit.SPADES))),
                    Arguments.of(List.of(new TrumpCard(Rank.JACK, Suit.CLUBS), new TrumpCard(Rank.KING, Suit.DIAMONDS), new TrumpCard(Rank.FOUR, Suit.HEARTS)))
            );
        }
    }
}
