package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class AceScoreCalculatorTest {
    @Test
    void 에이스가_한_개인_경우(){
        AceScoreCalculator aceScoreCalculator = new AceScoreCalculator();
        int sum = 10;
        int limit = 21;

        int aceScore = aceScoreCalculator.calculateAceScore(sum, 1, limit);

        assertThat(aceScore).isEqualTo(11);
    }
}
