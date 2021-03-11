package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {

    @Test
    @DisplayName("카드가 같은 심볼과 문양을 가지면 같은 객체로 인식하는지 확인하는 테스트.")
    public void init() {
        Card card = new Card(Symbol.TWO, Shape.DIAMOND);
        assertThat(card).isEqualTo(new Card(Symbol.TWO, Shape.DIAMOND));
    }
}
