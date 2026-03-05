package blackjack.domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.hand.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameResultTest {

    @Test
    @DisplayName("플레이어가 버스트이면 패이다")
    void of_returnsLose_whenPlayerIsBust() {
        assertThat(GameResult.of(new Score(23), new Score(18))).isEqualTo(GameResult.LOSE);
    }

    @Test
    @DisplayName("딜러가 버스트이면 승이다")
    void of_returnsWin_whenDealerIsBust() {
        assertThat(GameResult.of(new Score(18), new Score(23))).isEqualTo(GameResult.WIN);
    }

    @Test
    @DisplayName("플레이어 점수가 딜러보다 높으면 승이다")
    void of_returnsWin_whenPlayerScoreIsHigher() {
        assertThat(GameResult.of(new Score(20), new Score(18))).isEqualTo(GameResult.WIN);
    }

    @Test
    @DisplayName("플레이어 점수가 딜러보다 낮으면 패이다")
    void of_returnsLose_whenPlayerScoreIsLower() {
        assertThat(GameResult.of(new Score(18), new Score(20))).isEqualTo(GameResult.LOSE);
    }

    @Test
    @DisplayName("플레이어 점수와 딜러 점수가 같으면 무승부이다")
    void of_returnsDraw_whenScoresAreEqual() {
        assertThat(GameResult.of(new Score(20), new Score(20))).isEqualTo(GameResult.DRAW);
    }

    @Test
    @DisplayName("승의 반전은 패이다")
    void reverse_returnsLose_whenWin() {
        assertThat(GameResult.WIN.reverse()).isEqualTo(GameResult.LOSE);
    }

    @Test
    @DisplayName("패의 반전은 승이다")
    void reverse_returnsWin_whenLose() {
        assertThat(GameResult.LOSE.reverse()).isEqualTo(GameResult.WIN);
    }

    @Test
    @DisplayName("무승부의 반전은 무승부이다")
    void reverse_returnsDraw_whenDraw() {
        assertThat(GameResult.DRAW.reverse()).isEqualTo(GameResult.DRAW);
    }
}
