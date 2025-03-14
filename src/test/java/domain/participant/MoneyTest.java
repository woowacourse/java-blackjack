package domain.participant;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoneyTest {
    @Test
    @DisplayName("돈 객체 생성 테스트")
    void moneyTest() {
        assertDoesNotThrow(() -> new Money(10000));
    }

}
