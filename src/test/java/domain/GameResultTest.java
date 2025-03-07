package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class GameResultTest {

    @Nested
    class ValidCases {

        @ParameterizedTest
        @DisplayName("플레이어가 버스트이거나, 딜러보다 점수가 낮으면 LOSE를 반환한다.")
        @MethodSource("provideLoseCases")
        void playerLoses(Score playerScore, Score dealerScore) {
            // when
            GameResult result = GameResult.from(playerScore, dealerScore);

            // then
            assertThat(result).isEqualTo(GameResult.LOSE);
        }

        static Stream<Score[]> provideLoseCases() {
            return Stream.of(
                    new Score[]{Score.BUST, Score.TWENTY_ONE},
                    new Score[]{Score.BUST, Score.BUST},
                    new Score[]{Score.TEN, Score.ELEVEN},
                    new Score[]{Score.FIFTEEN, Score.TWENTY},
                    new Score[]{Score.SEVEN, Score.EIGHT}
            );
        }

        @ParameterizedTest
        @DisplayName("플레이어와 딜러의 점수가 같으면 DRAW를 반환한다.")
        @MethodSource("provideDrawCases")
        void gameIsDraw(Score playerScore, Score dealerScore) {
            // when
            GameResult result = GameResult.from(playerScore, dealerScore);

            // then
            assertThat(result).isEqualTo(GameResult.DRAW);
        }

        static Stream<Score[]> provideDrawCases() {
            return Stream.of(
                    new Score[]{Score.TWENTY_ONE, Score.TWENTY_ONE},
                    new Score[]{Score.TWENTY, Score.TWENTY},
                    new Score[]{Score.SEVENTEEN, Score.SEVENTEEN}
            );
        }

        @ParameterizedTest
        @DisplayName("플레이어 점수가 더 높으면 WIN을 반환한다.")
        @MethodSource("provideWinCases")
        void playerWins(Score playerScore, Score dealerScore) {
            // when
            GameResult result = GameResult.from(playerScore, dealerScore);

            // then
            assertThat(result).isEqualTo(GameResult.WIN);
        }

        static Stream<Score[]> provideWinCases() {
            return Stream.of(
                    new Score[]{Score.TWENTY_ONE, Score.TWENTY},
                    new Score[]{Score.NINETEEN, Score.SEVENTEEN},
                    new Score[]{Score.EIGHTEEN, Score.SEVEN},
                    new Score[]{Score.TEN, Score.NINE}
            );
        }
    }
}
