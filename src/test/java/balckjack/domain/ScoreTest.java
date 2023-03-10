package balckjack.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class ScoreTest {

    @Test
    void createInstanceWithValue() {
        Score score = new Score(4);
        Assertions.assertThat(score).isInstanceOf(Score.class);
    }

    @Test
    void testAddAceScore() {
        Score score = new Score(6);
        Assertions.assertThat(score.addAceScore(3).getValue()).isEqualTo(19);
    }

    @Test
    void testAddAceScore2() {
        Score score = new Score(18);
        Assertions.assertThat(score.addAceScore(3).getValue()).isEqualTo(21);
    }

    @Test
    void testAddAceScore3() {
        Score score = new Score(19);
        Assertions.assertThat(score.addAceScore(3).getValue()).isEqualTo(22);
    }

    @Test
    void calculateScore() {
        Score score = new Score(22);
        Assertions.assertThat(score.isBust()).isTrue();
    }

    @Test
    void isEqualScore() {
        Score score1 = new Score(10);
        Score score2 = new Score(10);
        Assertions.assertThat(score1.equals(score2)).isTrue();
    }

    @Test
    void isNotEqualScore() {
        Score score1 = new Score(10);
        Score score2 = new Score(11);
        Assertions.assertThat(score1.equals(score2)).isFalse();
    }

    @Test
    void isMoreThan() {
        Score score1 = new Score(12);
        Score score2 = new Score(11);
        Assertions.assertThat(score1.isMoreThan(score2)).isTrue();
    }

}