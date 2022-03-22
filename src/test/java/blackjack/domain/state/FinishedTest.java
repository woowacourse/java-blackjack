package blackjack.domain.state;

import static blackjack.domain.TestBlackjackUtils.createCardHand;
import static blackjack.domain.TestCardFixture.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FinishedTest {

    @DisplayName("draw했을 경우 예외 발생")
    @Test
    void throwExceptionWhenDraw() {
        Finished stay = new Stay(createCardHand(aceCard, twoCard));

        assertThatThrownBy(() -> stay.draw(threeCard))
            .isInstanceOf(UnsupportedOperationException.class)
            .hasMessageContaining("[ERROR] 카드뽑는걸 지원하지 않습니다.");
    }

    @DisplayName("stay했을 경우 예외 발생")
    @Test
    void throwExceptionWhenStay() {
        State stay = new Stay(createCardHand(aceCard, twoCard));

        assertThatThrownBy(stay::stay)
            .isInstanceOf(UnsupportedOperationException.class)
            .hasMessageContaining("[ERROR] 스테이를 지원하지 않습니다.");
    }

    @DisplayName("stay상태일 경우 종료상태인지 확인")
    @Test
    void isFinished() {
        State stay = new Stay(createCardHand(aceCard, twoCard));

        assertThat(stay.isFinished()).isTrue();
    }
}
