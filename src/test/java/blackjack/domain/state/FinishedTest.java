package blackjack.domain.state;

import static blackjack.domain.TestBlackjackUtils.createCardHand;
import static blackjack.domain.TestCardFixture.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FinishedTest {

    @Test
    void throwExceptionWhenDraw() {
        Finished stay = new Stay(createCardHand(aceCard, twoCard));

        assertThatThrownBy(() -> stay.draw(threeCard))
            .isInstanceOf(UnsupportedOperationException.class)
            .hasMessageContaining("[ERROR] 카드뽑는걸 지원하지 않습니다.");
    }

    @Test
    void throwExceptionWhenStay() {
        State stay = new Stay(createCardHand(aceCard, twoCard));

        assertThatThrownBy(stay::stay)
            .isInstanceOf(UnsupportedOperationException.class)
            .hasMessageContaining("[ERROR] 스테이를 지원하지 않습니다.");
    }

    @Test
    void isFinished() {
        State stay = new Stay(createCardHand(aceCard, twoCard));

        assertThat(stay.isFinished()).isTrue();
    }
}
