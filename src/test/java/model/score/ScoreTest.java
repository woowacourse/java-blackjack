package model.score;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import score.Score;

public class ScoreTest {
    @Test
    @DisplayName("compareTo() 올바르게 동작하는 지")
    void score() {
        //given
        Score score1 = new Score(1);
        Score score2 = new Score(3);
        //when
        //then
        Assertions.assertThat(score1.compareTo(score2)).isEqualTo(-1);
    }

    @Test
    @DisplayName("현재 점수가 블랙잭 점수 21점에 부합하는 지: true")
    void isSatisfiedBlackJack() {
        //given
        Score score = new Score(21);
        //when
        //then
        Assertions.assertThat(score.isSatisfiedBlackJackScore()).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints={20,22})
    @DisplayName("현재 점수가 블랙잭 점수 21점에 부합하는 지: true")
    void isNotSatisfiedBlackJack(int value) {
        //given
        Score score = new Score(value);
        //when
        //then
        Assertions.assertThat(score.isSatisfiedBlackJackScore()).isFalse();
    }
}
