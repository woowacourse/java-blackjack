package blackjack.game;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class GameResultTest {

    @Nested
    @DisplayName("승패 판단 테스트")
    class PlayerResultTest {

        @Test
        @DisplayName("플레이어의 카드합이 딜러보다 높다면 승리한다.")
        void isWin() {
            int dealerSum = 18;
            int playerSum = 21;

            GameResult playerResult = GameResult.fromDenominationsSum(dealerSum, playerSum);

            assertThat(playerResult).isEqualTo(GameResult.WIN);
        }

        @Test
        @DisplayName("플레이어의 카드합이 딜러와 같다면 비긴다.")
        void isDraw() {
            int dealerSum = 21;
            int playerSum = 21;

            GameResult playerResult = GameResult.fromDenominationsSum(dealerSum, playerSum);

            assertThat(playerResult).isEqualTo(GameResult.DRAW);
        }

        @Test
        @DisplayName("플레이어의 카드합이 딜러보다 낮다면 패배한다.")
        void isLose() {
            int dealerSum = 21;
            int playerSum = 20;

            GameResult playerResult = GameResult.fromDenominationsSum(dealerSum, playerSum);

            assertThat(playerResult).isEqualTo(GameResult.LOSE);
        }
    }

    @Nested
    @DisplayName("승패 확인 테스트")
    class ResultTest {

        @Test
        @DisplayName("승리를 확인할 수 있다.")
        void checkWin() {
            GameResult playerResult = GameResult.WIN;

            assertThat(playerResult.isWin()).isTrue();
        }

        @Test
        @DisplayName("무승부를 확인할 수 있다.")
        void checkDraw() {
            GameResult playerResult = GameResult.DRAW;

            assertThat(playerResult.isDraw()).isTrue();
        }

        @Test
        @DisplayName("패배를 확인할 수 있다.")
        void checkLose() {
            GameResult playerResult = GameResult.LOSE;

            assertThat(playerResult.isLose()).isTrue();
        }
    }
}
