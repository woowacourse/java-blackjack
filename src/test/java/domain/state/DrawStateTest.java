package domain.state;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DrawStateTest {
    @Test
    @DisplayName("HITTING 상태에서 NORMAL이면 계속 진행한다")
    void keepHittingWhenNormal() {
        DrawState nextState = DrawState.HITTING.hit(HandState.NORMAL);

        assertEquals(DrawState.HITTING, nextState);
        assertFalse(nextState.isFinished());
    }

    @Test
    @DisplayName("HITTING 상태에서 BUST면 FINISHED로 전이한다")
    void finishWhenBust() {
        DrawState nextState = DrawState.HITTING.hit(HandState.BUST);

        assertEquals(DrawState.FINISHED, nextState);
        assertTrue(nextState.isFinished());
    }

    @Test
    @DisplayName("HITTING 상태에서 stand를 선택하면 FINISHED가 된다")
    void finishWhenStand() {
        DrawState nextState = DrawState.HITTING.stand();

        assertEquals(DrawState.FINISHED, nextState);
        assertTrue(nextState.isFinished());
    }

    @Test
    @DisplayName("FINISHED 상태에서는 hit과 stand 모두 FINISHED를 유지한다")
    void keepFinishedState() {
        assertEquals(DrawState.FINISHED, DrawState.FINISHED.hit(HandState.NORMAL));
        assertEquals(DrawState.FINISHED, DrawState.FINISHED.stand());
        assertTrue(DrawState.FINISHED.isFinished());
    }
}
