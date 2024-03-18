package domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ScoreTest {
    @DisplayName("덧셈을 계산한다.")
    @Test
    void add() {
        final Score score = new Score(5);
        final Score newScore = score.add(2);

        assertEquals(7, newScore.toInt());
    }

    @DisplayName("ACE 점수를 최적화하여 계산한다.")
    @Test
    void ace() {
        final Score score = new Score(20);
        final Score newScore = score.addAce();

        assertEquals(21, newScore.toInt());
    }
}
