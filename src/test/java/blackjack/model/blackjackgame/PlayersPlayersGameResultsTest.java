package blackjack.model.blackjackgame;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.model.participants.Player;
import blackjack.view.DealerResultStatus;
import blackjack.view.PlayerResultStatus;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PlayersPlayersGameResultsTest {
    @DisplayName("게임 결과로 딜러 결과를 출력한다.")
    @ParameterizedTest
    @CsvSource(value = {"1,2,3", "2,3,4", "10,1,9"})
    void getDealerResult(int winCount, int loseCount, int pushCount) {
        PlayersGameResults playersGameResults = new PlayersGameResults(createResults(winCount, loseCount, pushCount));
        Map<DealerResultStatus, Long> dealerResult = playersGameResults.getDealerResult();

        assertAll(
                () -> assertThat(dealerResult.get(DealerResultStatus.WIN)).isEqualTo(loseCount),
                () -> assertThat(dealerResult.get(DealerResultStatus.LOSE)).isEqualTo(winCount),
                () -> assertThat(dealerResult.get(DealerResultStatus.PUSH)).isEqualTo(pushCount)
        );
    }

    private Map<Player, PlayerResultStatus> createResults(int win, int lose, int push) {
        Map<Player, PlayerResultStatus> map = new HashMap<>();
        for (int i = 0; i < win; i++) {
            map.put(new Player("daon" + i), PlayerResultStatus.WIN);
        }
        for (int i = 0; i < lose; i++) {
            map.put(new Player("pobi" + i), PlayerResultStatus.LOSE);
        }
        for (int i = 0; i < push; i++) {
            map.put(new Player("woni" + i), PlayerResultStatus.PUSH);
        }
        return map;
    }
}
