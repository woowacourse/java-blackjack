package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class GameResultTest {

    @Nested
    @DisplayName("딜러 승패 결과 테스트")
    class DealerResultTest {

        @Test
        @DisplayName("플레이어가 승리하면 딜러는 패배한다.")
        void changeWin() {
            GameResult playerResult = GameResult.WIN;

            assertThat(playerResult.changeStatusOpposite()).isEqualTo(GameResult.LOSE);
        }

        @Test
        @DisplayName("플레이어가 패배하면 딜러는 승리한다.")
        void changeLose() {
            GameResult playerResult = GameResult.LOSE;

            assertThat(playerResult.changeStatusOpposite()).isEqualTo(GameResult.WIN);
        }

        @Test
        @DisplayName("무승부는 모두 무승부를 반환한다.")
        void stayDraw() {
            GameResult playerResult = GameResult.DRAW;

            assertThat(playerResult.changeStatusOpposite()).isEqualTo(GameResult.DRAW);
        }
    }
}
