package blackjack.domain;

import blackjack.exception.InvalidNameInputException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class GameResultTest {

    @DisplayName("게임결과 통계 검증")
    @Test
    void checkStatistics() throws InvalidNameInputException {
        Players players = new Players(Arrays.asList(TestSetUp.createBlackJackPlayer(), TestSetUp.createLoser(), TestSetUp.createTiePlayer(), TestSetUp.createBustPlayer()));
        GameResult gameResult = players.match(TestSetUp.createDealer());

        Map<ResultType, Integer> expected = new HashMap<>();
        expected.put(ResultType.WIN, 2);
        expected.put(ResultType.TIE, 1);
        expected.put(ResultType.LOSE, 1);
        assertThat(gameResult.getDealerStatistics()).isEqualTo(expected);
    }
}
