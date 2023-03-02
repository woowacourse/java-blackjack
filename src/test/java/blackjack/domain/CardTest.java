package blackjack.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {

    @DisplayName("카드의 숫자와 모양을 전달하면 카드가 생성된다.")
    @Test
    void Should_Create_When_NewCard() {
        Assertions.assertDoesNotThrow(() -> new Card(CardNumber.EIGHT, CardSymbol.HEARTS));
    }
}
