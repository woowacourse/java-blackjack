package blackjack.domain.result;

import blackjack.domain.hand.Score;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GameResultCalculatorTest {

    private final GameResultCalculator calculator = new GameResultCalculator();

    @Test
    void 플레이어가_버스트이면_패이다() {
        assertThat(calculator.calculate(new Score(23), new Score(18))).isEqualTo(GameResult.LOSE);
    }

    @Test
    void 딜러가_버스트이면_승이다() {
        assertThat(calculator.calculate(new Score(18), new Score(23))).isEqualTo(GameResult.WIN);
    }

    @Test
    void 플레이어_점수가_딜러보다_높으면_승이다() {
        assertThat(calculator.calculate(new Score(20), new Score(18))).isEqualTo(GameResult.WIN);
    }

    @Test
    void 플레이어_점수가_딜러보다_낮으면_패이다() {
        assertThat(calculator.calculate(new Score(18), new Score(20))).isEqualTo(GameResult.LOSE);
    }

    @Test
    void 플레이어_점수와_딜러_점수가_같으면_무승부이다() {
        assertThat(calculator.calculate(new Score(20), new Score(20))).isEqualTo(GameResult.DRAW);
    }
}
