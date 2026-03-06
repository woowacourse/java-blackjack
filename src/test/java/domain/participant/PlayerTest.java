package domain.participant;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import domain.Rank;
import domain.Suit;
import domain.card.Card;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    @Test
    void 플레이어가_정상적으로_생성되어야_한다() {
        assertDoesNotThrow(() -> new Player("minseo"));
    }
}
