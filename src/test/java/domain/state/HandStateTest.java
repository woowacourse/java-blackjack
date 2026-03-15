package domain.state;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HandStateTest {
    @Test
    @DisplayName("초기 2장으로 21이면 BLACKJACK 상태다")
    void blackjackWithInitialCards() {
        HandState handState = HandState.getState(21, true);

        assertEquals(HandState.BLACKJACK, handState);
        assertTrue(handState.isBlackjack());
    }

    @Test
    @DisplayName("초기 2장이 아닌 21점은 NORMAL 상태다")
    void normalWithNonInitialCards() {
        HandState handState = HandState.getState(21, false);

        assertEquals(HandState.NORMAL, handState);
        assertTrue(handState.isNormal());
        assertFalse(handState.isBlackjack());
    }

    @Test
    @DisplayName("21점을 초과하면 BUST 상태다")
    void bustState() {
        HandState handState = HandState.getState(22, false);

        assertEquals(HandState.BUST, handState);
        assertTrue(handState.isBust());
    }

    @Test
    @DisplayName("NORMAL 상태에서 동점이면 무승부다")
    void drawWhenSameScoreInNormal() {
        Outcome outcome = HandState.NORMAL.against(HandState.NORMAL, 20, 20);

        assertEquals(Outcome.DRAW, outcome);
    }
}
