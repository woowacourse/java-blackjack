package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ScoreTest {

    @Test
    @DisplayName("Score에 다른 score를 더할 수 있다.")
    void createScoreAndCheckCache() {
        Score firstScore = Score.from(1);
        Score secondScore = Score.from(2);

        assertThat(firstScore.add(secondScore)).isEqualTo(Score.from(3));
    }

    @ParameterizedTest
    @CsvSource(value = {"1:2:true", "2:2:true", "3:2:false"}, delimiter = ':')
    @DisplayName("두 score의 같거나 작음을 비교할 수 있다.")
    void compareScoresLessThanEqual(int first, int second, boolean isTrue) {
        Score firstScore = Score.from(first);
        Score secondScore = Score.from(second);

        assertThat(firstScore.isLessThanOrEqual(secondScore)).isEqualTo(isTrue);
    }

    @Test
    @DisplayName("score는 ACE가 있고 기준점을 넘는 경우, 점수를 감소시킨다.")
    void decreaseScoreByAce() {
        Score score = Score.from(25);
        int aceCount = 1;
        Score limit = Score.from(21);

        assertThat(score.decreaseScoreByAce(limit, aceCount)).isEqualTo(Score.from(15));
    }

    @Test
    @DisplayName("score 는 21점을 넘기면 bust이다.")
    void scoreIsBustWhenOverBlackjack() {
        Score score = Score.from(22);

        assertThat(score.isBust()).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"2:1:true", "2:2:false"}, delimiter = ':')
    @DisplayName("두 score의 중 큰 값을 비교할 수 있다.")
    void compareScoreGreaterThan(int first, int second, boolean isTrue) {
        Score firstScore = Score.from(first);
        Score secondScore = Score.from(second);

        assertThat(firstScore.isGreaterThan(secondScore)).isEqualTo(isTrue);
    }

    @Test
    @DisplayName("같은 값을 가진 Score는 캐싱된 score가 반환된다.")
    void createScoreWithCache() {
        Score firstScore = Score.from(1);
        Score secondScore = Score.from(1);

        assertThat(firstScore).isSameAs(secondScore);
    }

}