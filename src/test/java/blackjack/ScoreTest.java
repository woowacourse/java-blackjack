package blackjack;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ScoreTest {

    @ParameterizedTest(name = "입력값 : {1}")
    @CsvSource({"22,True", "21,False", "17,False"})
    void 버스트_발생(int scoreValue, boolean expect) {
        assertThat(new Score(scoreValue).isBust()).isEqualTo(expect);
    }

    @ParameterizedTest
    @DisplayName("less than 테스트")
    @CsvSource({"7,8,True", "8,7,False", "8,8,False"})
    void compareLessThan(int input1, int input2, boolean expect) {
        Score score1 = new Score(input1);
        Score score2 = new Score(input2);
        assertThat(score1.lessThan(score2)).isEqualTo(expect);
    }

    @ParameterizedTest
    @DisplayName("more than 테스트")
    @CsvSource({"7,8,False", "8,7,True", "8,8,False"})
    void compareMoreThan(int input1, int input2, boolean expect) {
        Score score1 = new Score(input1);
        Score score2 = new Score(input2);
        assertThat(score1.moreThan(score2)).isEqualTo(expect);
    }
}
