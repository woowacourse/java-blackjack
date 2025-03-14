package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class CardTest {

    @Test
    @DisplayName("카드는 숫자와 모양을 가진다.")
    void card() {
        // given
        Card card = new Card(CardNumber.EIGHT, CardShape.CLOVER);

        // when & than
        assertAll(
                () -> assertThat(card.getValue()).isEqualTo(8),
                () -> assertThat(card.shape()).isEqualTo(CardShape.CLOVER)
        );
    }
}
