package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {
    @DisplayName("올바르게 카드 시그니처를 반환한다.")
    @Test
    void getSignatureTest() {
        Number number = Number.EIGHT;
        Shape shape = Shape.CLOVER;
        Card card = new Card(number, shape);
        assertThat(card.getSignature()).isEqualTo("8클로버");
    }
}
