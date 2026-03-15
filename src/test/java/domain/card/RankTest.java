package domain.card;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RankTest {
    @Test
    @DisplayName("Rank는 symbol과 점수를 제공한다")
    void rankValue() {
        assertEquals("A", Rank.ACE.symbol());
        assertEquals(11, Rank.ACE.value());
        assertTrue(Rank.ACE.isAce());
    }
}
