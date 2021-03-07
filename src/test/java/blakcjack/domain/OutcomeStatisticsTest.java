package blakcjack.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class OutcomeStatisticsTest {
    private Map<Outcome, Integer> dealerOutcome;
    private Map<String, Outcome> playersOutcome;

    @BeforeEach
    void setUp() {
        dealerOutcome = new LinkedHashMap<>();
        dealerOutcome.put(Outcome.WIN, 2);
        dealerOutcome.put(Outcome.DRAW, 1);
        dealerOutcome.put(Outcome.LOSE, 0);

        playersOutcome = new LinkedHashMap<>();
        playersOutcome.put("pobi", Outcome.LOSE);
        playersOutcome.put("sakjung", Outcome.DRAW);
        playersOutcome.put("mediumBear", Outcome.LOSE);
    }

    @DisplayName("객체 생성 성공")
    @Test
    void create() {
        final OutcomeStatistics outcomeStatistics = new OutcomeStatistics(dealerOutcome, playersOutcome);
        assertThat(outcomeStatistics.getDealerOutcome()).isEqualTo(dealerOutcome);
        assertThat(outcomeStatistics.getPlayersOutcome()).isEqualTo(playersOutcome);
    }
}