package domain.result;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class OutcomeTest {

    @DisplayName("WIN이면 LOSE, LOSE면 WIN, DRAW면 DRAW를 반환한다.")
    @Test
    void reverseOutcome() {
        assertSame(Outcome.LOSE, Outcome.reverse(Outcome.WIN));
        assertSame(Outcome.WIN, Outcome.reverse(Outcome.LOSE));
        assertSame(Outcome.DRAW, Outcome.reverse(Outcome.DRAW));
    }

    @DisplayName("점수가 높으면 WIN, 낮으면 LOSE, 같으면 DRAW를 반환한다.")
    @Test
    void decideOutcome() {
        int highScore = 21;
        int lowScore = 11;
        int sameScore = 20;

        int otherScore = 20;

        assertEquals(Outcome.WIN, Outcome.decide(highScore, otherScore));
        assertEquals(Outcome.LOSE, Outcome.decide(lowScore, otherScore));
        assertEquals(Outcome.DRAW, Outcome.decide(sameScore, otherScore));
    }
}
