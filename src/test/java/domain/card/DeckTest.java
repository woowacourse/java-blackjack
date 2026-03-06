package domain.card;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

public class DeckTest {
    @Test
    void 덱_생성_시_정상적으로_생성되어야_한다() {
        assertDoesNotThrow(() -> new Deck());
    }
}
