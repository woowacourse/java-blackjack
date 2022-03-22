package blackjack.domain.state;

import static blackjack.domain.TestBlackjackUtils.createCardHand;
import static blackjack.domain.TestCardFixture.*;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HitTest {

    @DisplayName("draw를 했을 경우 bust 상태로 변하는지 확인")
    @Test
    void drawAfterBust() {
        Hit hit = new Hit(createCardHand(tenCard, tenCard));
        State afterState = hit.draw(twoCard);

        assertThat(afterState).isInstanceOf(Bust.class);
    }

    @DisplayName("draw를 했을 경우 hit 상태로 변하는지 확인")
    @Test
    void drawAfterHit() {
        Hit hit = new Hit(createCardHand(twoCard, tenCard));
        State afterState = hit.draw(fiveCard);

        assertThat(afterState).isInstanceOf(Hit.class);
    }

    @DisplayName("draw를 했을 경우 stay 상태로 변하는지 확인")
    @Test
    void stay() {
        Hit hit = new Hit(createCardHand(tenCard, fiveCard));
        State afterState = hit.stay();

        assertThat(afterState).isInstanceOf(Stay.class);
    }

    @DisplayName("hit 상태에서 running 상태인지 확인")
    @Test
    void isRunning() {
        Hit hit = new Hit(createCardHand(tenCard, fiveCard));

        assertThat(hit.isRunning()).isTrue();
    }
}
