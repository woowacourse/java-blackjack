package blackjack.domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.hand.Score;
import org.junit.jupiter.api.Test;

class GameResultTest {

    @Test
    void 플레이어가_버스트이면_패이다() {
        assertThat(GameResult.of(new Score(23), new Score(18))).isEqualTo(GameResult.LOSE);
    }

    @Test
    void 딜러가_버스트이면_승이다() {
        assertThat(GameResult.of(new Score(18), new Score(23))).isEqualTo(GameResult.WIN);
    }

    @Test
    void 플레이어_점수가_딜러보다_높으면_승이다() {
        assertThat(GameResult.of(new Score(20), new Score(18))).isEqualTo(GameResult.WIN);
    }

    @Test
    void 플레이어_점수가_딜러보다_낮으면_패이다() {
        assertThat(GameResult.of(new Score(18), new Score(20))).isEqualTo(GameResult.LOSE);
    }

    @Test
    void 플레이어_점수와_딜러_점수가_같으면_무승부이다() {
        assertThat(GameResult.of(new Score(20), new Score(20))).isEqualTo(GameResult.DRAW);
    }

    @Test
    void 승의_반전은_패이다() {
        assertThat(GameResult.WIN.reverse()).isEqualTo(GameResult.LOSE);
    }

    @Test
    void 패의_반전은_승이다() {
        assertThat(GameResult.LOSE.reverse()).isEqualTo(GameResult.WIN);
    }

    @Test
    void 무승부의_반전은_무승부이다() {
        assertThat(GameResult.DRAW.reverse()).isEqualTo(GameResult.DRAW);
    }
}
