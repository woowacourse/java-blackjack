package domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import domain.BlackJackWinningStatus;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerResultsTest {

    @Test
    @DisplayName("플레이어 이름을 토대로 블랙잭 승패 결과를 가져온다.")
    void should_return_playerWinningStatus_by_name() {
        String playerName = "a";
        PlayerWinningStatus playerWinningStatus = new PlayerWinningStatus(playerName, BlackJackWinningStatus.WIN);
        List<PlayerWinningStatus> playerWinningStatuses = new ArrayList<>(List.of(playerWinningStatus));
        PlayerResults playerResults = new PlayerResults(playerWinningStatuses);

        // when
        PlayerWinningStatus foundPlayerWinningStatus = playerResults.findByPlayerName(playerName);

        // then
        assertThat(foundPlayerWinningStatus).isEqualTo(playerWinningStatus);
    }
}
