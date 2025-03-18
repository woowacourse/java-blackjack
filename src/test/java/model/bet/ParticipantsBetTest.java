package model.bet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;
import model.result.BettingResults;
import model.result.GameResult;
import model.result.GameResults;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantsBetTest {

    @DisplayName("GameResults를 통해 BettingResults를 계산한다.")
    @Test
    void calculateBettingResultsTest() {
        ParticipantsBet participantsBet = new ParticipantsBet(Map.of(
                "pobi", 10_000,
                "jason", 20_000,
                "danny", 5_000,
                "hotteok", 50_000
        ));
        GameResults gameResults = new GameResults(Map.of(
                "pobi", GameResult.WIN,
                "jason", GameResult.BLACKJACK_WIN,
                "danny", GameResult.DRAW,
                "hotteok", GameResult.LOSE
        ));

        BettingResults bettingResults = participantsBet.calculateBettingResults(gameResults);

        assertAll(
                () -> assertThat(bettingResults.getBettingResultByName("pobi")).isEqualTo(10_000),
                () -> assertThat(bettingResults.getBettingResultByName("jason")).isEqualTo(30_000),
                () -> assertThat(bettingResults.getBettingResultByName("danny")).isEqualTo(0),
                () -> assertThat(bettingResults.getBettingResultByName("hotteok")).isEqualTo(-50_000)
        );
    }
}
