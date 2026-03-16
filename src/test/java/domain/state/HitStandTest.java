package domain.state;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HitStandTest {
    @Test
    @DisplayName("입력값 y/n은 유효하다")
    void validateInput() {
        assertTrue(HitStand.validate("y"));
        assertTrue(HitStand.validate("n"));
        assertFalse(HitStand.validate("x"));
    }

    @Test
    @DisplayName("y 입력은 HIT으로 판정한다")
    void hitDecision() {
        assertTrue(HitStand.isHit("y"));
        assertFalse(HitStand.isHit("n"));
    }
}
