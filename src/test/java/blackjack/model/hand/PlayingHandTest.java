package blackjack.model.hand;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class PlayingHandTest {

    @Test
    void 히트할_수_있는_상태라고_판단한다() {
        // given
        PlayingHand hand = new HitHand(List.of());

        // when
        boolean canHit = hand.canHit();

        // then
        assertThat(canHit).isTrue();
    }
}
