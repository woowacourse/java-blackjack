package domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class CardTest {

    @Test
    void 정상적으로_생성된다() {
        //given,when,then
        Assertions.assertDoesNotThrow(() -> new Card(Suit.HEART, Denomination.QUEEN));
    }
}
