package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ScoreTest {

    @DisplayName("점수를 더한다")
    @Test
    public void add() {
        Score score1 = new Score(1);
        Score score2 = new Score(1);

        Score newScore = score1.add(score2);

        assertThat(newScore).isEqualTo(new Score(2));
    }
    
}
