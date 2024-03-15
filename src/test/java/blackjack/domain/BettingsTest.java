package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.participant.ParticipantName;
import blackjack.domain.result.WinStatus;
import blackjack.domain.result.WinningResult;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class BettingsTest {
    @DisplayName("플레이어의 승패에 따라 최종 수익을 구한다.")
    @ParameterizedTest
    @CsvSource(value = {
            "WIN, 10000, 20000",
            "WIN_BLACKJACK, 15000, 30000",
            "LOSE, -10000, -20000",
            "DRAW, 0, 0"})
    void getBettingResults(WinStatus winStatus, int pobiExpected, int jasonExpected) {
        // given
        WinningResult winningResult = new WinningResult(Map.of(
                new ParticipantName("pobi"), winStatus,
                new ParticipantName("jason"), winStatus));

        PlayerBetting pobiBetting = new PlayerBetting("pobi", 10000);
        PlayerBetting jasonBetting = new PlayerBetting("jason", 20000);

        List<PlayerBetting> playerBettings = new ArrayList<>();
        playerBettings.add(pobiBetting);
        playerBettings.add(jasonBetting);

        // when
        BettingResults bettingResults = BettingResults.of(new PlayerBettings(playerBettings), winningResult);

        // then
        assertThat(bettingResults.getBettingResults()).contains(
                new BettingResult(new ParticipantName("pobi"), pobiExpected),
                new BettingResult(new ParticipantName("jason"), jasonExpected));
    }
}
