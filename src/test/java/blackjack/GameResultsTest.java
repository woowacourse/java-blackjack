package blackjack;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.model.Cards;
import blackjack.model.GameResults;
import blackjack.model.Player;
import blackjack.model.ResultStatus;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class GameResultsTest {
    @DisplayName("게임 결과로 딜러 결과를 출력한다.")
    @ParameterizedTest
    @CsvSource(value = {"1,2,3", "2,3,4", "10,1,9"})
    void getDealerResult(int winCount, int loseCount, int pushCount) {
        GameResults gameResults = new GameResults(createResults(winCount, loseCount, pushCount));
        Map<ResultStatus, Long> dealerResult = gameResults.getDealerResult();

        assertAll(
                () -> assertThat(dealerResult.get(ResultStatus.WIN)).isEqualTo(winCount),
                () -> assertThat(dealerResult.get(ResultStatus.LOSE)).isEqualTo(loseCount),
                () -> assertThat(dealerResult.get(ResultStatus.PUSH)).isEqualTo(pushCount)
        );
    }

    private Map<Player, ResultStatus> createResults(int win, int lose, int push) {
        Map<Player, ResultStatus> map = new HashMap<>();
        for (int i = 0; i < win; i++) {
            map.put(new Player("daon" + i, new Cards()), ResultStatus.WIN);
        }
        for (int i = 0; i < lose; i++) {
            map.put(new Player("pobi" + i, new Cards()), ResultStatus.LOSE);
        }
        for (int i = 0; i < push; i++) {
            map.put(new Player("woni" + i, new Cards()), ResultStatus.PUSH);
        }
        return map;
    }
}
