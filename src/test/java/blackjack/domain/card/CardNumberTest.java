package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

// TODO: 테스트 리팩토링 - 단위테스트, 파라미터라이즈드, 픽스처
class CardNumberTest {

    @Test
    @DisplayName("J, Q, K는 각각 10으로 계산한다")
    void cardNumberTest() {
        assertThat(CardNumber.JACK.getNumber()).isEqualTo(10);
        assertThat(CardNumber.QUEEN.getNumber()).isEqualTo(10);
        assertThat(CardNumber.KING.getNumber()).isEqualTo(10);
    }
}
