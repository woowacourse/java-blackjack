import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackTest {

    @Test
    @DisplayName("카드는 4가지 문양을 가질 수 있다")
    void cardTypeTest() {
        assertThat(CardType.values()).hasSize(4);
    }

    @Test
    @DisplayName("J, Q, K는 각각 10으로 계산한다")
    void cardNumberTest() {
        assertThat(CardNumber.JACK.getNumber()).isEqualTo(10);
        assertThat(CardNumber.QUEEN.getNumber()).isEqualTo(10);
        assertThat(CardNumber.KING.getNumber()).isEqualTo(10);
    }
}
