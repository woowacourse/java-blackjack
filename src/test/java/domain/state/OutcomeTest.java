package domain.state;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OutcomeTest {
    @Test
    @DisplayName("Outcome은 결과 라벨을 제공한다")
    void resultLabel() {
        assertEquals("승", Outcome.WIN.result());
        assertEquals("패", Outcome.LOSE.result());
    }
}
