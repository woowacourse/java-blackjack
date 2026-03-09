package blackjack.domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.hand.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameResultTest {

    @Test
    @DisplayName("플레이어가 버스트이면 패이다")
    void of_returnsLose_whenPlayerIsBust() {
        // given & when
        GameResult result = GameResult.of(new Score(23), new Score(18));

        // then
        assertThat(result).isEqualTo(GameResult.LOSE);
    }

    @Test
    @DisplayName("딜러가 버스트이면 승이다")
    void of_returnsWin_whenDealerIsBust() {
        // given & when
        GameResult result = GameResult.of(new Score(18), new Score(23));

        // then
        assertThat(result).isEqualTo(GameResult.WIN);
    }

    @Test
    @DisplayName("플레이어 점수가 딜러보다 높으면 승이다")
    void of_returnsWin_whenPlayerScoreIsHigher() {
        // given & when
        GameResult result = GameResult.of(new Score(20), new Score(18));

        // then
        assertThat(result).isEqualTo(GameResult.WIN);
    }

    @Test
    @DisplayName("플레이어 점수가 딜러보다 낮으면 패이다")
    void of_returnsLose_whenPlayerScoreIsLower() {
        // given & when
        GameResult result = GameResult.of(new Score(18), new Score(20));

        // then
        assertThat(result).isEqualTo(GameResult.LOSE);
    }

    @Test
    @DisplayName("플레이어 점수와 딜러 점수가 같으면 무승부이다")
    void of_returnsDraw_whenScoresAreEqual() {
        // given & when
        GameResult result = GameResult.of(new Score(20), new Score(20));

        // then
        assertThat(result).isEqualTo(GameResult.DRAW);
    }

    @Test
    @DisplayName("승의 반전은 패이다")
    void reverse_returnsLose_whenWin() {
        // given & when
        GameResult result = GameResult.WIN.reverse();

        // then
        assertThat(result).isEqualTo(GameResult.LOSE);
    }

    @Test
    @DisplayName("패의 반전은 승이다")
    void reverse_returnsWin_whenLose() {
        // given & when
        GameResult result = GameResult.LOSE.reverse();

        // then
        assertThat(result).isEqualTo(GameResult.WIN);
    }

    @Test
    @DisplayName("무승부의 반전은 무승부이다")
    void reverse_returnsDraw_whenDraw() {
        // given & when
        GameResult result = GameResult.DRAW.reverse();

        // then
        assertThat(result).isEqualTo(GameResult.DRAW);
    }
}
