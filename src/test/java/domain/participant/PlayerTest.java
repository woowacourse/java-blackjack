package domain.participant;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import domain.card.Hand;
import java.util.List;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    @Test
    void 플레이어가_정상적으로_생성되어야_한다() {
        assertDoesNotThrow(() -> Player.of(Hand.of(List.of()), "jeje", "1000"));
    }
}
