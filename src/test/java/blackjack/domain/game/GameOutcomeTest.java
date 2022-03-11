package blackjack.domain.game;

import static blackjack.domain.game.GameOutcome.DRAW;
import static blackjack.domain.game.GameOutcome.LOSE;
import static blackjack.domain.game.GameOutcome.WIN;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameOutcomeTest {

    @Test
    @DisplayName("수가 크면 승을 반환받을 수 있다.")
    void calculateWin() {
        assertThat(GameOutcome.calculateOutcome(10, 5)).isEqualTo(WIN);
    }

    @Test
    @DisplayName("수가 작으면 패를 반환받을 수 있다.")
    void calculateLose() {
        assertThat(GameOutcome.calculateOutcome(5, 10)).isEqualTo(LOSE);
    }

    @Test
    @DisplayName("수가 같으면 무승부을 반환받을 수 있다.")
    void calculateDraw() {
        assertThat(GameOutcome.calculateOutcome(10, 10)).isEqualTo(DRAW);
    }
}
