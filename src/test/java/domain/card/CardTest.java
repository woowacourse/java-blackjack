package domain.card;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {
    
    @Test
    @DisplayName("카드는 자신의 점수를 반환한다.")
    void getScore() {
        Card card = new Card(CardNumber.KING, CardShape.SPADE);
        CardNumber cardNumber = card.getCardNumber();
        CardShape shape = card.getCardShape();
        Assertions.assertThat(cardNumber).isEqualTo(CardNumber.KING);
        Assertions.assertThat(shape).isEqualTo(CardShape.SPADE);
    }
}
