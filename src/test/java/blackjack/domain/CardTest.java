package blackjack.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {

    @Test
    @DisplayName("심볼과 카드번호를 주면 카드를 제대로 만들 수 있는지")
    void makeCard_returnRightValue() {
        int expected = CardNumber.ACE.getCardNumberValue();
        Card aceClover = new Card(Symbol.CLOVER, CardNumber.ACE);
        Assertions.assertThat(aceClover.getCardNumberValue()).isEqualTo(expected);
    }
}
