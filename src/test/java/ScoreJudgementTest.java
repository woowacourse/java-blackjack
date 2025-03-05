import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ScoreJudgementTest {

    @DisplayName("21이 넘지 않으면서 가장 큰 수를 반환한다.")
    @Test
    void test1() {
        // given
        ScoreJudgement scoreJudgement = new ScoreJudgement();
        List<Integer> candidates = List.of(19, 20, 3, 7, 22);

        // when
        int result = scoreJudgement.determineNearest(candidates);

        // then
        Assertions.assertThat(result).isEqualTo(20);
    }

    @DisplayName("모든 수가 21이 넘으면 0을 반환한다.")
    @Test
    void test2() {
        // given
        ScoreJudgement scoreJudgement = new ScoreJudgement();
        List<Integer> candidates = List.of(22, 23, 24, 25, 26);

        // when
        int result = scoreJudgement.determineNearest(candidates);

        // then
        Assertions.assertThat(result).isEqualTo(0);
    }

    @DisplayName("숫자 합이 21에 가까우면 이긴다.")
    @Test
    void test3() {
        // given
        int total1 = 20;
        int total2 = 19;
        ScoreJudgement judgement = new ScoreJudgement();

        // when
        int result = judgement.judgeWinner(total1, total2);

        // then
        Assertions.assertThat(result).isEqualTo(-1);
    }

    @DisplayName("숫자 합이 둘이 같으면 무승부")
    @Test
    void test4() {
        // given
        int total1 = 10;
        int total2 = 10;
        ScoreJudgement judgement = new ScoreJudgement();

        // when
        int result = judgement.judgeWinner(total1, total2);

        // then
        Assertions.assertThat(result).isEqualTo(0);
    }
}
