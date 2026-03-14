package domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class RankTest {

    @Test
    void 에이스가_11이_될_수_있으면_11을_적용한다() {
        Score result = Rank.totalSum(1, new Score(10));

        Assertions.assertEquals(result.value(), 21);
    }

    @Test
    void 에이스가_11이_될_수_없으면_1을_적용한다() {
        Score result = Rank.totalSum(1, new Score(11));

        Assertions.assertEquals(result.value(), 12);
    }
}
