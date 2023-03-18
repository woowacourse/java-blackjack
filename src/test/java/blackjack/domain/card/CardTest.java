package blackjack.domain.card;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {

    @Test
    @DisplayName("Card 생성자는 숫자와 기호를 받아 카드를 생성한다.")
    void generate_card() {
        // given & when
        CardNumber number = CardNumber.THREE;
        CardSymbol symbol = CardSymbol.HEART;

        // then
        Assertions.assertThatNoException().isThrownBy(() -> new Card(number, symbol));
    }
}
