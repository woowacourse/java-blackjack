package domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static domain.Constant.ACE_MAX_VALUE;
import static domain.Constant.ACE_MIN_VALUE;

public class RankTest {

    @Test
    void 에이스와의_합이_21이하인_경우_11을_반환해야_한다() {
        Score result = Rank.decideAceValue(new Score(10), 1);

        Assertions.assertEquals(result.getValue(), ACE_MAX_VALUE);
    }

    @Test
    void 에이스와의_합이_21초과인_경우_1을_반환해야_한다() {
        Score result = Rank.decideAceValue(new Score(11), 1);

        Assertions.assertEquals(result.getValue(), ACE_MIN_VALUE);
    }
}
