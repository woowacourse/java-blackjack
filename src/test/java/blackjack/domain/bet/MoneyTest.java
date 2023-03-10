package blackjack.domain.bet;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class MoneyTest {

    @Test
    @DisplayName("금액이 정상 생성된다")
    void moneyInitializeTest() {
        assertDoesNotThrow(() -> new Money(5000));
    }

}
