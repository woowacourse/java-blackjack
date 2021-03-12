package blackjack.domain.gametable;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Score;
import blackjack.domain.gametable.Outcome;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OutcomeTest {

    @Test
    @DisplayName("base:21, counterpart:22 경우 -> base 승")
    void result0() {
        assertThat(Outcome.getInstance(Score.of(21), Score.of(22))).isEqualTo(Outcome.WIN);
    }

    @Test
    @DisplayName("base: 20, counterpart:22 -> base 승")
    void result2() {
        assertThat(Outcome.getInstance(Score.of(20), Score.of(22))).isEqualTo(Outcome.WIN);
    }

    @Test
    @DisplayName("base: 20, counterpart:19 -> base 승")
    void result7() {
        assertThat(Outcome.getInstance(Score.of(20), Score.of(19))).isEqualTo(Outcome.WIN);
    }

    @Test
    @DisplayName("base:20, counterpart:21 경우 -> base 패")
    void result1() {
        assertThat(Outcome.getInstance(Score.of(20), Score.of(21))).isEqualTo(Outcome.LOSE);
    }

    @Test
    @DisplayName("base:20, counterpart:20 -> base 무")
    void result3() {
        assertThat(Outcome.getInstance(Score.of(20), Score.of(20))).isEqualTo(Outcome.DRAW);
    }

    @Test
    @DisplayName("base:21, counterpart:21 -> base 무")
    void result4() {
        assertThat(Outcome.getInstance(Score.of(21), Score.of(21))).isEqualTo(Outcome.DRAW);
    }

    @Test
    @DisplayName("base:22, counterpart:22 -> base 무")
    void result5() {
        assertThat(Outcome.getInstance(Score.of(22), Score.of(22))).isEqualTo(Outcome.DRAW);
    }

    @Test
    @DisplayName("base:23, counterpart:22 -> base 무")
    void result6() {
        assertThat(Outcome.getInstance(Score.of(23), Score.of(22))).isEqualTo(Outcome.DRAW);
    }
}
