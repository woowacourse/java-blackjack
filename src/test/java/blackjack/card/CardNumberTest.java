package blackjack.card;

import static card.CardNumber.ACE;
import static card.CardNumber.TWO;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardNumberTest {

    @Test
    @DisplayName("ACE의 label A를 가져올 수 있다.")
    void getLabelOne() {
        assertThat(ACE.getLabel()).isEqualTo("A");
    }

    @Test
    @DisplayName("TWO의 label \"2\"를 가져올 수 있다.")
    void getLabelTwo() {
        assertThat(TWO.getLabel()).isEqualTo("2");
    }

    @Test
    @DisplayName("ACE의 value 1을 가져올 수 있다.")
    void getValueOne() {
        assertThat(ACE.getValue()).isEqualTo(1);
    }

    @Test
    @DisplayName("TWO의 value 1을 가져올 수 있다.")
    void getValueTwo() {
        assertThat(TWO.getValue()).isEqualTo(2);
    }
}
