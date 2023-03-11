package domain.card;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Hand는 ")
class HandTest {
    @Test
    @DisplayName("비어있는 Hand를 나타낼 수 있다.")
    void createHandTest() {
        assertDoesNotThrow(() -> new Hand());
    }

    @Test
    @DisplayName("카드를 한 장 받을 수 있다.")
    void addTest() {
        Card card = CloverCard.CLOVER_FOUR;
        Hand hand = new Hand();

        assertDoesNotThrow(() -> hand.add(card));
    }
}
