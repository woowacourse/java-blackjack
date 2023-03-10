package blackjack.domain;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameResultTest {

    private static GameResult gameResult;

    @BeforeAll
    static void initResult() {
        Map<Player, ResultState> result = Map.of(
                Player.from("a"), ResultState.WIN,
                Player.from("b"), ResultState.DRAW,
                Player.from("c"), ResultState.LOSE,
                Player.from("d"), ResultState.WIN,
                Player.from("e"), ResultState.DRAW,
                Player.from("f"), ResultState.DRAW
        );

        gameResult = new GameResult(result);
    }

    @Test
    void getDealerWinCountTest() {
        assertEquals(gameResult.getDealerWinCount(), 1);
    }

    @Test
    void getDealerLoseCount() {
        assertEquals(gameResult.getDealerLoseCount(), 2);
    }

    @Test
    void getDealerDrawCount() {
        assertEquals(gameResult.getDealerDrawCount(), 3);
    }

    @Test
    void getResultStateByPlayer() {
        Player player = Player.from("a");
        assertEquals(ResultState.WIN.getValue(), gameResult.getResultStateByPlayer(player));
    }
}
