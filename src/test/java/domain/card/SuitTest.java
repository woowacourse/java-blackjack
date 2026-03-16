package domain.card;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SuitTest {
    @Test
    @DisplayName("Suit는 한글 문양명을 반환한다")
    void suitName() {
        assertEquals("스페이드", Suit.SPADE.suit());
        assertEquals("하트", Suit.HEART.suit());
    }
}
