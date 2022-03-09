package blackjack;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

public class ScoreTest {

    @ParameterizedTest
    @CsvSource({"17,20,LOSS", "20,17,WIN", "19,19,DRAW"})
    void 점수_비교_테스트(int value1, int value2, String result) {
        Score score1 = new Score(value1);
        Score score2 = new Score(value2);
        assertThat(score1.compare(score2)).isEqualTo(Result.valueOf(result));
    }

    @ParameterizedTest
    @CsvSource({"17,24,WIN", "24,17,LOSS", "25,22,DRAW"})
    void 버스트_점수_비교(int value1, int value2, String result) {
        Score score1 = new Score(value1);
        Score score2 = new Score(value2);
        assertThat(score1.compare(score2)).isEqualTo(Result.valueOf(result));
    }

    @ParameterizedTest(name = "입력값 : {1}")
    @CsvSource({"22,True", "21,False", "17,False"})
    void 버스트_발생(int scoreValue, boolean expect) {
        assertThat(new Score(scoreValue).isBust()).isEqualTo(expect);
    }
}
