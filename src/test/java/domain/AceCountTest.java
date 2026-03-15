package domain;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AceCountTest {
    @Test
    @DisplayName("AceCount는 증가 후 감소를 정상 반영한다")
    void increaseAndDecrease() {
        AceCount aceCount = AceCount.zero();

        assertFalse(aceCount.hasAny());
        assertTrue(aceCount.increase().hasAny());
        assertFalse(aceCount.increase().decrease().hasAny());
    }
}
