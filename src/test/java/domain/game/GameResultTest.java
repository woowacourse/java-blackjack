package domain.game;

import static org.junit.jupiter.api.Assertions.assertEquals;

import domain.state.Outcome;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GameResultTest {
    @Test
    @DisplayName("플레이어 결과와 딜러 집계 결과를 조회할 수 있다")
    void readResult() {
        GameResult gameResult = new GameResult(
                Map.of("pobi", Outcome.WIN),
                Map.of(Outcome.WIN, 0, Outcome.LOSE, 1)
        );

        assertEquals(Outcome.WIN, gameResult.getPlayerOutcome("pobi"));
        assertEquals(1, gameResult.getDealerCount(Outcome.LOSE));
    }
}
