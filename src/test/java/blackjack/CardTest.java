package blackjack;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {

    @Test
    @DisplayName("카드 값과 카드 문양을 통해 카드를 생성한다.")
    public void Card_Instance_create_with_cardValue_and_cardSymbol() {
        Assertions.assertThatCode(() -> {
            new Card(CardValue.TWO, CardSymbol.DIAMOND);
        }).doesNotThrowAnyException();
    }
}
