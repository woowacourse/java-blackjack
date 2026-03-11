package blackjack.model.hand;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class FinishedHandTest {

    @Test
    void 히트할_수_없는_상태라고_판단한다() {
        // given
        FinishedHand hand = new BlackjackHand(List.of());

        // when
        boolean canHit = hand.canHit();

        // then
        assertThat(canHit).isFalse();
    }
}
