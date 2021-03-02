package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {

    @Test
    @DisplayName("카드가 정상적으로 symbol과 shape을 가지는 지 테스트")
    public void init() {
        Card card = new Card(Symbol.TWO, Shape.DIAMOND);
        assertThat(card).isEqualTo(new Card(Symbol.TWO, Shape.DIAMOND));
    }
}
