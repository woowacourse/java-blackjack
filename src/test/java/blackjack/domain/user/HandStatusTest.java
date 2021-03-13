package blackjack.domain.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HandStatusTest {

    @DisplayName("블랙잭인 경우")
    @Test
    void calculateStatus_Blackjack() {
        HandStatus status = HandStatus.calculateStatus(21, 2);
        assertEquals(HandStatus.BLACK_JACK, status);
    }

    @DisplayName("버스트인 경우")
    @Test
    void calculateStatus_Bust() {
        HandStatus status = HandStatus.calculateStatus(25, 2);
        assertEquals(HandStatus.BUST, status);
    }

    @DisplayName("히트 경우")
    @Test
    void calculateStatus_Hit() {
        HandStatus status = HandStatus.calculateStatus(16, 2);
        assertEquals(HandStatus.HIT, status);
    }
}