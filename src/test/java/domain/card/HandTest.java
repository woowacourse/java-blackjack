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
}
