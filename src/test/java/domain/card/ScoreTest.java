package domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ScoreTest {

    @DisplayName("22이면 10을 빼서 12이다")
    @Test
    void minusTenIfBust_minus() {
        Score score = new Score(22);
       Score newScore = score.minusTenIfBust();

       assertThat(newScore).isEqualTo(new Score(12));
    }

    @DisplayName("21이면 빼지 않는다")
    @Test
    void minusTenIfBust_not() {
        Score score = new Score(21);
        Score newScore = score.minusTenIfBust();

        assertThat(newScore).isEqualTo(new Score(21));
    }
}
