package card;

import static card.CardNumber.ACE;
import static card.CardNumber.TWO;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardNumberTest {

    @Test
    @DisplayName("ACE의 label A를 가져올 수 있다.")
    void getValueOne() {
        assertThat(ACE.getLabel()).isEqualTo("A");
    }

    @Test
    @DisplayName("TWO의 label \"2\"를 가져올 수 있다.")
    void getValueTwo() {
        assertThat(TWO.getLabel()).isEqualTo("2");
    }
}
