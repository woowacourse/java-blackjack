package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ScoreTest {
    @Test
    void isBiggerThan() {
        Score score1 = new Score(21);
        Score score2 = new Score(20);

        boolean isBigger = score1.isBiggerThan(score2);

        assertThat(isBigger).isTrue();
    }

    @Test
    void isNotBiggerThan() {
        Score score1 = new Score(21);
        Score score2 = new Score(22);

        boolean isBigger = score1.isBiggerThan(score2);

        assertThat(isBigger).isFalse();
    }
}