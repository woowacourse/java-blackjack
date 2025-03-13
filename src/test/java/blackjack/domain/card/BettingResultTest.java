package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class BettingResultTest {
    @Nested
    class 점수에_따라_베팅금액을_계산할_수_있다 {
        @Test
        void 플레이어_점수가_21을_초과하는_경우() {
            //given
            Score score = new Score(22);

            //when
            int multiplyRatio = BettingResult.getMultiplyRatio(score);

            //then
            assertThat(multiplyRatio).isEqualTo(-1);
        }
    }
}
