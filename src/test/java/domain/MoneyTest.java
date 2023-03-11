package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoneyTest {

    @Test
    @DisplayName("money를 생성한다.")
    void test_create() {
        assertDoesNotThrow(() -> new Money(1_000));
    }
}