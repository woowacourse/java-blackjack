package blackjack.domain.state;

import static blackjack.domain.TestBlackjackUtils.createCardHand;
import static blackjack.domain.TestCardFixture.*;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class HitTest {

    @Test
    void drawAfterBust() {
        Hit hit = new Hit(createCardHand(tenCard, tenCard));
        State afterState = hit.draw(twoCard);

        assertThat(afterState).isInstanceOf(Bust.class);
    }

    @Test
    void drawAfterHit() {
        Hit hit = new Hit(createCardHand(twoCard, tenCard));
        State afterState = hit.draw(fiveCard);

        assertThat(afterState).isInstanceOf(Hit.class);
    }

    @Test
    void stay() {
        Hit hit = new Hit(createCardHand(tenCard, fiveCard));
        State afterState = hit.stay();

        assertThat(afterState).isInstanceOf(Stay.class);
    }

    @Test
    void isRunning() {
        Hit hit = new Hit(createCardHand(tenCard, fiveCard));

        assertThat(hit.isRunning()).isTrue();
    }
}
