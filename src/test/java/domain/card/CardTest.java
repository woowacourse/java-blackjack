package domain.card;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardTest {
    @Test
    @DisplayName("카드의 모양을 반환한다.")
    void getCardType() {
        Assertions.assertThat(Card.from(CardType.SPADE, CardNumber.TWO).getCardType())
                .isEqualTo(CardType.SPADE);
    }

    @Test
    @DisplayName("카드의 점수를 반환한다.")
    void getCardNumber() {
        Assertions.assertThat(Card.from(CardType.SPADE, CardNumber.TWO).getCardNumber())
                .isEqualTo(CardNumber.TWO);
    }
}
