package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class ResultTest {
    @Nested
    class DeterminePlayerResultTest {
        @Test
        @DisplayName("플레이어가 버스트라면 딜러의 버스트 여부와 상관없이 무조건 패배한다.")
        void shouldReturnLossWhenPlayerIsBust() {
            // given
            boolean isDealerBust = true;
            boolean isPlayerBust = true;
            int dealerScore = 23;
            int playerScore = 22;

            // when
            Result result = Result.determinePlayerResult(isDealerBust, isPlayerBust, dealerScore, playerScore);

            // then
            assertThat(result).isEqualTo(Result.LOSS);
        }

        @Test
        @DisplayName("플레이어는 버스트가 아니고 딜러만 버스트인 경우 승리를 반환한다.")
        void shouldReturnWinWhenPlayerIsNotBustButDealerIsBust() {
            // given
            boolean isDealerBust = true;
            boolean isPlayerBust = false;
            int dealerScore = 23;
            int playerScore = 15;

            // when
            Result result = Result.determinePlayerResult(isDealerBust, isPlayerBust, dealerScore, playerScore);

            // then
            assertThat(result).isEqualTo(Result.WIN);
        }

        @Test
        @DisplayName("플레이어와 딜러 모두 버스트가 아니고, 플레이어 점수가 딜러 점수보다 높으면 승리를 반환한다.")
        void shouldReturnWinWhenNeitherIsBustAndPlayerScoreIsHigherThanDealerScore() {
            // given
            boolean isDealerBust = false;
            boolean isPlayerBust = false;
            int dealerScore = 17;
            int playerScore = 20;

            // when
            Result result = Result.determinePlayerResult(isDealerBust, isPlayerBust, dealerScore, playerScore);

            // then
            assertThat(result).isEqualTo(Result.WIN);
        }

        @Test
        @DisplayName("플레이어와 딜러 모두 버스트가 아니고, 플레이어 점수가 딜러 점수보다 낮으면 패배를 반환한다.")
        void shouldReturnLossWhenNeitherIsBustAndPlayerScoreIsLowerThanDealerScore() {
            // given
            boolean isDealerBust = false;
            boolean isPlayerBust = false;
            int dealerScore = 20;
            int playerScore = 15;

            // when
            Result result = Result.determinePlayerResult(isDealerBust, isPlayerBust, dealerScore, playerScore);

            // then
            assertThat(result).isEqualTo(Result.LOSS);
        }

        @Test
        @DisplayName("플레이어와 딜러 모두 버스트가 아니고, 플레이어 점수와 딜러 점수가 같다면 무승부를 반환한다.")
        void shouldReturnTieWhenNeitherIsBustAndScoresAreEqual() {
            // given
            boolean isDealerBust = false;
            boolean isPlayerBust = false;
            int dealerScore = 20;
            int playerScore = 20;

            // when
            Result result = Result.determinePlayerResult(isDealerBust, isPlayerBust, dealerScore, playerScore);

            // then
            assertThat(result).isEqualTo(Result.TIE);
        }
    }

    @Nested
    class ReverseTest {
        @Test
        @DisplayName("승리를 뒤집으면 패배가 된다.")
        void shouldReturnLossWhenReversingWin() {
            assertThat(Result.WIN.reverse()).isEqualTo(Result.LOSS);
        }

        @Test
        @DisplayName("패배를 뒤집으면 승리가 된다.")
        void shouldReturnWinWhenReversingLoss() {
            assertThat(Result.LOSS.reverse()).isEqualTo(Result.WIN);
        }

        @Test
        @DisplayName("무승부를 뒤집으면 그대로 무승부가 된다.")
        void shouldReturnTieWhenReversingTie() {
            assertThat(Result.TIE.reverse()).isEqualTo(Result.TIE);
        }
    }
}
