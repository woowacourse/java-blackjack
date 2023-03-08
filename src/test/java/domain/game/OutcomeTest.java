package domain.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OutcomeTest {

    @DisplayName("WIN이면 LOSE, LOSE면 WIN, DRAW면 DRAW를 반환한다.")
    @Test
    void reverseOutcome() {
        assertSame(Outcome.LOSE, Outcome.reverseOutcome(Outcome.WIN));
        assertSame(Outcome.WIN, Outcome.reverseOutcome(Outcome.LOSE));
        assertSame(Outcome.DRAW, Outcome.reverseOutcome(Outcome.DRAW));
    }

    @DisplayName("점수가 높으면 WIN, 낮으면 LOSE, 같으면 DRAW를 반환한다.")
    @Test
    void decideOutcome() {
        int highScore = 21;
        int lowScore = 11;
        int sameScore = 20;

        int otherScore = 20;

        assertEquals(Outcome.WIN, Outcome.decideOutcome(highScore, otherScore));
        assertEquals(Outcome.LOSE, Outcome.decideOutcome(lowScore, otherScore));
        assertEquals(Outcome.DRAW, Outcome.decideOutcome(sameScore, otherScore));
    }
}
