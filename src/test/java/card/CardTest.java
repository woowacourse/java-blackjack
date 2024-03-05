package card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CardTest {

    @Test
    @DisplayName("카드의 점수를 정확하게 계산한다.")
    void calculateCardTest() {
        Card card = new Card(Symbol.SPADE, Number.ACE);
        assertThat(card.getScore()).isEqualTo(1);
    }
}
