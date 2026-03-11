package domain;

import static domain.Rank.ACE_MAX_VALUE;
import static domain.Rank.ACE_MIN_VALUE;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RankTest {

    @Test
    void 에이스와의_합이_21이하인_경우_11을_반환해야_한다() {
        int result = Rank.decideAceValue(10, 1);

        Assertions.assertEquals(result, ACE_MAX_VALUE);
    }

    @Test
    void 에이스와의_합이_21초과인_경우_1을_반환해야_한다() {
        int result = Rank.decideAceValue(11, 1);

        Assertions.assertEquals(result, ACE_MIN_VALUE);
    }
}
