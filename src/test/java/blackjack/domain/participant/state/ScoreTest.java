package blackjack.domain.participant.state;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.Result;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ScoreTest {

    @Test
    @DisplayName("점수 생성 테스트")
    void testCreateScore() {
        Score score = new Score(15);
        assertThat(score.getValue()).isEqualTo(15);
    }

    @Test
    @DisplayName("점수 버스트 테스트")
    void testScoreBust() {
        Score notBustScore = new Score(21);
        assertThat(notBustScore.isBust()).isFalse();

        Score bustScore = new Score(22);
        assertThat(bustScore.isBust()).isTrue();
    }

    @Test
    @DisplayName("점수 블랙잭 테스트")
    void testScoreBlackjack() {
        Score notBlackjackScore = new Score(20);
        assertThat(notBlackjackScore.isBlackjack()).isFalse();

        Score blackjackScore = new Score(21);
        assertThat(blackjackScore.isBlackjack()).isTrue();
    }

    @Test
    @DisplayName("점수 버스트일 경우 줄이기 테스트")
    void testReduceScoreIfBust() {
        Score score = new Score(25);
        assertThat(score.isBust()).isTrue();
        assertThat(score.reduceScoreIfBust().isBust()).isFalse();
    }

    @Test
    @DisplayName("점수 비교 테스트")
    void testCompareScore() {
        Score compareScore = new Score(17);
        Score firstScore = new Score(15);
        Score secondScore = new Score(19);
        Score thirdScore = new Score(17);

        assertThat(compareScore.compare(firstScore)).isEqualTo(Result.WIN);
        assertThat(compareScore.compare(secondScore)).isEqualTo(Result.LOSE);
        assertThat(compareScore.compare(thirdScore)).isEqualTo(Result.DRAW);
    }
}