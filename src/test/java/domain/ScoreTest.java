package domain;

import static org.assertj.core.api.Assertions.assertThat;

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
                    Arguments.of(List.of(new TrumpCard(Rank.ACE, Suit.SPADES), new TrumpCard(Rank.KING, Suit.HEARTS)), Score.BLACKJACK),
                    Arguments.of(List.of(new TrumpCard(Rank.TEN, Suit.DIAMONDS), new TrumpCard(Rank.JACK, Suit.HEARTS)), Score.TWENTY),
                    Arguments.of(List.of(new TrumpCard(Rank.NINE, Suit.CLUBS), new TrumpCard(Rank.SEVEN, Suit.DIAMONDS)), Score.SIXTEEN),
                    Arguments.of(List.of(new TrumpCard(Rank.FIVE, Suit.SPADES), new TrumpCard(Rank.THREE, Suit.DIAMONDS)), Score.EIGHT),
                    Arguments.of(List.of(new TrumpCard(Rank.KING, Suit.CLUBS), new TrumpCard(Rank.QUEEN, Suit.HEARTS), new TrumpCard(Rank.TWO, Suit.SPADES)),
                            Score.BUST)
            );
        }
    }

    @ParameterizedTest
    @DisplayName("점수를 비교한다.")
    @MethodSource("provideScoreComparisonCases")
    void isLowerThan(Score firstScore, Score secondScore, boolean expectedResult) {
        // when
        boolean result = firstScore.isLowerThan(secondScore);

        // then
        assertThat(result).isEqualTo(expectedResult);
    }

    static Stream<Arguments> provideScoreComparisonCases() {
        return Stream.of(
                Arguments.of(Score.BUST, Score.TWENTY_ONE, true),
                Arguments.of(Score.TEN, Score.ELEVEN, true),
                Arguments.of(Score.FIFTEEN, Score.TWENTY, true),
                Arguments.of(Score.TWENTY, Score.TWENTY, false),
                Arguments.of(Score.TWENTY_ONE, Score.TWENTY, false)
        );
    }
}
