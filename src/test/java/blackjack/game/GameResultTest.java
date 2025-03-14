package blackjack.game;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class GameResultTest {

    @Nested
    @DisplayName("승패 확인 테스트")
    class DealerResultTest {

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
