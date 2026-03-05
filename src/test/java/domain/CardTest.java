package domain;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

public class CardTest {
    @Test
    void 카드가_정상적으로_생성되어야_한다() {
        assertDoesNotThrow(() -> new Card(Suit.SPADE, Rank.ACE));
    }
}
