package blackjack.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class GameResultTest {

    @Test
    void name() {
        Players players = new Players(Arrays.asList(TestSetUp.WINNER, TestSetUp.LOSER, TestSetUp.TIE_PLAYER, TestSetUp.BUST_PLAYER));
        GameResult gameResult = players.match(TestSetUp.DEALER);

        Map<ResultType, Integer> expected = new HashMap<>();
        expected.put(ResultType.WIN, 1);
        expected.put(ResultType.TIE, 1);
        expected.put(ResultType.LOSE, 2);
        assertThat(gameResult.getStatistics()).isEqualTo(expected);
    }
}
