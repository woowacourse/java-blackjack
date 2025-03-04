import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ScoreJudgementTest {

    @DisplayName("숫자 합이 21이 넘으면 진다.")
    @Test
    void test1() {
        // given
        int total = 22;
        ScoreJudgement judgement = new ScoreJudgement();

        // when
        boolean result = judgement.isOverCriterion(total);

        // then
        Assertions.assertThat(result).isFalse();
    }
}
