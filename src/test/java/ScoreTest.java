import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ScoreTest {

    @DisplayName("A를 제외한 나머지 카드의 합이 10 이하인 경우 A는 11로 계산한다.")
    @Test
    void calculateScoreWhenUnderThresholdTest() {
        Score score = new Score(0);
        Assertions.assertThat(score.addScore(new Card(CardNumber.A, CardShape.CLOVER)))
                .isEqualTo(11);
    }

    @DisplayName("A를 제외한 나머지 카드의 합이 10 초과인 경우 A는 1로 계산한다.")
    @Test
    void calculateScoreWhenOverThresholdTest() {
        Score score = new Score(0);
        score.addScore(new Card(CardNumber.K, CardShape.CLOVER));
        score.addScore(new Card(CardNumber.K, CardShape.SPADE));
        Assertions.assertThat(score.addScore(new Card(CardNumber.A, CardShape.CLOVER)))
                .isEqualTo(21);
    }

    @DisplayName("지금까지 뽑은 카드 숫자의 합을 저장한다.")
    @Test
    void saveTotalCardNumbersTest() {
        Score score = new Score(0);
        Assertions.assertThat(score.addScore(new Card(CardNumber.FIVE, CardShape.CLOVER)))
                .isEqualTo(5);
    }

    @DisplayName("지금까지 뽑은 카드 숫자의 합이 21 미만인지 알려준다.")
    @Test
    void lowerThanThresholdTest() {
        Score score = new Score(0);
        score.addScore(new Card(CardNumber.THREE, CardShape.CLOVER));
        Assertions.assertThat(score.isLowerThanThreshold())
                .isTrue();
    }
}
