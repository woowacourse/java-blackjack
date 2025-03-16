package blackjack.domain.result;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class ScoreTest {

    @Test
    void 점수가_21이_넘으면_busted로_판단한다() {
        // given
        Score score = new Score(22);

        // when
        boolean isBusted = score.isBusted();

        // then
        assertTrue(isBusted);
    }

}
