package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.participant.state.hand.Score;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class GameResultTest {

    @Nested
    class ValidCases {

        @ParameterizedTest
        @DisplayName("플레이어가 버스트이거나, 딜러보다 점수가 낮으면 LOSE를 반환한다.")
        @MethodSource("provideLoseCases")
        void playerLoses(Score playerScore, Score dealerScore) {
            GameResult result = GameResult.of(playerScore, dealerScore);
            assertThat(result).isEqualTo(GameResult.LOSE);
        }

        static Stream<Arguments> provideLoseCases() {
            return Stream.of(
                    Arguments.of(Score.BUST, Score.TWENTY_ONE),
                    Arguments.of(Score.BUST, Score.BUST),
                    Arguments.of(Score.TEN, Score.ELEVEN),
                    Arguments.of(Score.FIFTEEN, Score.TWENTY),
                    Arguments.of(Score.SEVEN, Score.EIGHT)
            );
        }

        @ParameterizedTest
        @DisplayName("플레이어와 딜러의 점수가 같으면 DRAW를 반환한다.")
        @MethodSource("provideDrawCases")
        void gameIsDraw(Score playerScore, Score dealerScore) {
            GameResult result = GameResult.of(playerScore, dealerScore);
            assertThat(result).isEqualTo(GameResult.DRAW);
        }

        static Stream<Arguments> provideDrawCases() {
            return Stream.of(
                    Arguments.of(Score.TWENTY_ONE, Score.TWENTY_ONE),
                    Arguments.of(Score.BLACKJACK, Score.BLACKJACK),
                    Arguments.of(Score.TWENTY, Score.TWENTY),
                    Arguments.of(Score.SEVENTEEN, Score.SEVENTEEN)
            );
        }

        @ParameterizedTest
        @DisplayName("플레이어가 블랙잭이면 BLACKJACK_WIN을을 반환한다.")
        @MethodSource("provideBlackJackWinCases")
        void playerBlackJackWins(Score playerScore, Score dealerScore) {
            GameResult result = GameResult.of(playerScore, dealerScore);
            assertThat(result).isEqualTo(GameResult.BLACKJACK_WIN);
        }

        static Stream<Arguments> provideBlackJackWinCases() {
            return Stream.of(
                    Arguments.of(Score.BLACKJACK, Score.TWENTY),
                    Arguments.of(Score.BLACKJACK, Score.SEVENTEEN),
                    Arguments.of(Score.BLACKJACK, Score.SEVEN),
                    Arguments.of(Score.BLACKJACK, Score.BUST)
            );
        }

        @ParameterizedTest
        @DisplayName("플레이어 점수가 더 높으면 WIN을 반환한다.")
        @MethodSource("provideWinCases")
        void playerWins(Score playerScore, Score dealerScore) {
            GameResult result = GameResult.of(playerScore, dealerScore);
            assertThat(result).isEqualTo(GameResult.WIN);
        }

        static Stream<Arguments> provideWinCases() {
            return Stream.of(
                    Arguments.of(Score.TWENTY_ONE, Score.TWENTY),
                    Arguments.of(Score.NINETEEN, Score.SEVENTEEN),
                    Arguments.of(Score.EIGHTEEN, Score.SEVEN),
                    Arguments.of(Score.TEN, Score.NINE)
            );
        }
    }
}
