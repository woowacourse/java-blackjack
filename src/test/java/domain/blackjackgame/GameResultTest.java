package domain.blackjackgame;

import static fixture.PlayersFixture.플레이어;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class GameResultTest {
    @ParameterizedTest
    @CsvSource(value = {"WIN:LOSE", "DRAW:DRAW", "LOSE:WIN"}, delimiter = ':')
    void 딜러의_결과는_플레이어의_결과와_반대이다(ResultStatus playerStatus, ResultStatus dealerStatus) {
        GameResult gameResult = new GameResult();
        gameResult.record(플레이어("프린"), playerStatus);

        Map<ResultStatus, Integer> dealerResult = gameResult.getDealerResult();

        assertThat(dealerResult).hasSize(1);
        assertThat(dealerResult).containsEntry(dealerStatus, 1);
    }
}
