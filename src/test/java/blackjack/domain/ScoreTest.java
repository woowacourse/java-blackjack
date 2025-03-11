package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ScoreTest {

    @DisplayName("가능한 최대 점수를 계산한다. (21이 넘지 않는 최대 점수)")
    @Test
    void test_calculateBestScore() {
        // given
        Set<Integer> possibleScores = Set.of(20,21,22);

        // when
        Score score = new Score(possibleScores);

        // then
        assertThat(score.intValue()).isEqualTo(21);
    }

    @DisplayName("21 초과 시 스코어는 버스트이다.")
    @ParameterizedTest
    @CsvSource({
            "22, true",
            "21, false"
    })
    void test_isBust(int scoreValue, boolean expected) {
        // given
        Score score = new Score(Set.of(scoreValue));

        // when
        boolean result = score.isBust();

        // then
        assertThat(result).isEqualTo(expected);
    }
    
    @DisplayName("21이고 2장이면 스코어는 블랙잭이다.")
    @Test
    void test_Blackjack() {
        // given
        Set<Integer> possibleScores = Set.of(21);
        Score score = new Score(possibleScores);

        // when
        boolean bust = score.isBlackjack(2);

        // then
        assertThat(bust).isTrue();
    }

    @DisplayName("21이지만 2장이 아니면 블랙잭이 아니다.")
    @Test
    void test_Blackjack_notTwice() {
        // given
        Set<Integer> possibleScores = Set.of(21);
        Score score = new Score(possibleScores);

        // when
        boolean bust = score.isBlackjack(3);

        // then
        assertThat(bust).isFalse();
    }

    @DisplayName("21이 아니면 블랙잭이 아니다.")
    @Test
    void test_Blackjack_False() {
        // given
        Set<Integer> possibleScores = Set.of(20);
        Score score = new Score(possibleScores);

        // when
        boolean bust = score.isBlackjack(2);

        // then
        assertThat(bust).isFalse();
    }

    @DisplayName("스코어가 21 이하인지 판단한다.")
    @ParameterizedTest
    @CsvSource({
            "20, true",
            "21, true",
            "22, false"
    })
    void test_isUnderGoal(int scoreValue, boolean expected) {
        // given
        Score score = new Score(Set.of(scoreValue));

        // when
        boolean result = score.isUnderGoal();

        // then
        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("현재 스코어가 다른 스코어보다 높은지 판단한다.")
    @Test
    void test_isHigherThan() {
        // given
        Score lowerScore = new Score(Set.of(20));
        Score higherScore = new Score(Set.of(21));

        // when & then
        assertThat(higherScore.isHigherThan(lowerScore)).isTrue();
        assertThat(lowerScore.isHigherThan(higherScore)).isFalse();
    }

}