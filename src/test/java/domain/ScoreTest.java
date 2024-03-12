package domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ScoreTest {
    @DisplayName("덧셈을 계산한다.")
    @Test
    void add() {
        // given
        final Score score = new Score(5);
        // when
        final Score newScore = score.add(2);
        //then
        Assertions.assertEquals(7, newScore.toInt());
    }

    @DisplayName("ACE 점수를 최적화하여 계산한다.")
    @Test
    void ace() {
        // given
        final Score score = new Score(20);
        // when
        final Score newScore = score.addAce();
        //then
        Assertions.assertEquals(21, newScore.toInt());
    }
}