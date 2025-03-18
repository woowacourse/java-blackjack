package domain.participant.state.hand;

import static org.assertj.core.api.Assertions.assertThat;

import domain.TrumpCard;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ScoreTest {

    @Nested
    class ValidCases {

        @ParameterizedTest
        @DisplayName("카드들로 점수를 계산한다.")
        @MethodSource("provideScoreCases")
        void calculateScore(List<TrumpCard> cards, Score expectedScore) {
            // when
            Score result = Score.from(cards);

            // then
            assertThat(result).isEqualTo(expectedScore);
        }

        static Stream<Arguments> provideScoreCases() {
            return Stream.of(
                    Arguments.of(List.of(TrumpCard.ACE_OF_SPADES, TrumpCard.KING_OF_HEARTS), Score.BLACKJACK),
                    Arguments.of(List.of(TrumpCard.TEN_OF_DIAMONDS, TrumpCard.JACK_OF_HEARTS), Score.TWENTY),
                    Arguments.of(List.of(TrumpCard.NINE_OF_CLUBS, TrumpCard.SEVEN_OF_DIAMONDS), Score.SIXTEEN),
                    Arguments.of(List.of(TrumpCard.FIVE_OF_SPADES, TrumpCard.THREE_OF_DIAMONDS), Score.EIGHT),
                    Arguments.of(List.of(TrumpCard.KING_OF_CLUBS, TrumpCard.QUEEN_OF_HEARTS, TrumpCard.TWO_OF_SPADES),
                            Score.BUST)
            );
        }

        @ParameterizedTest
        @DisplayName("점수의 작음을 비교한다.")
        @MethodSource("provideScoreLowerComparisonCases")
        void isLowerThan(Score firstScore, Score secondScore, boolean expectedResult) {
            // when
            boolean result = firstScore.isLowerThan(secondScore);

            // then
            assertThat(result).isEqualTo(expectedResult);
        }

        static Stream<Arguments> provideScoreLowerComparisonCases() {
            return Stream.of(
                    Arguments.of(Score.BUST, Score.TWENTY_ONE, true),
                    Arguments.of(Score.TEN, Score.ELEVEN, true),
                    Arguments.of(Score.FIFTEEN, Score.TWENTY, true),
                    Arguments.of(Score.TWENTY, Score.TWENTY, false),
                    Arguments.of(Score.TWENTY_ONE, Score.TWENTY, false)
            );
        }

        @ParameterizedTest
        @DisplayName("점수의 큼을 비교한다.")
        @MethodSource("provideScoreHigherComparisonCases")
        void isHigherThan(Score firstScore, Score secondScore, boolean expectedResult) {
            // when
            boolean result = firstScore.isHigherThan(secondScore);

            // then
            assertThat(result).isEqualTo(expectedResult);
        }

        static Stream<Arguments> provideScoreHigherComparisonCases() {
            return Stream.of(
                    Arguments.of(Score.BUST, Score.TWENTY_ONE, false),
                    Arguments.of(Score.TEN, Score.ELEVEN, false),
                    Arguments.of(Score.FIFTEEN, Score.TWENTY, false),
                    Arguments.of(Score.TWENTY, Score.TWENTY, false),
                    Arguments.of(Score.TWENTY_ONE, Score.TWENTY, true)
            );
        }
    }
}
