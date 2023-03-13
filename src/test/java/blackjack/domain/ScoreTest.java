package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ScoreTest {

    @Test
    void createInstanceWithValue() {
        Score score = new Score(4);
        Assertions.assertThat(score).isInstanceOf(Score.class);
    }

    @ParameterizedTest
    @CsvSource(value = {"18:21", "19:22", "6:19"}, delimiter = ':')
    void testAddAceScore(int input, int expected) {
        Score score = new Score(input);
        Assertions.assertThat(score.addAceScore(3).getValue()).isEqualTo(expected);
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