package model.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {

    @DisplayName("가지고 있는 카드 수 중에 작은 수를 반환한다.")
    @Test
    void minimumNumber() {
        Card card = new Card(CardShape.SPACE, CardNumber.ACE);
        assertThat(card.minimumNumber()).isEqualTo(1);
    }

    @DisplayName("가지고 있는 카드 수의 차이를 반환한다.")
    @Test
    void subtractMaxMinNumber() {
        Card card = new Card(CardShape.SPACE, CardNumber.ACE);
        assertThat(card.subtractMaxMinNumber()).isEqualTo(10);
    }

}
