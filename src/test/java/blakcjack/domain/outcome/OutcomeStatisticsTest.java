package blakcjack.domain.outcome;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class OutcomeStatisticsTest {
	private Map<String, Outcome> playersOutcome = new LinkedHashMap<>();
	private Map<Outcome, Integer> dealerOutcome = new LinkedHashMap<>();

	@BeforeEach
	void setUp() {
		playersOutcome.put("pobi", Outcome.LOSE);
		playersOutcome.put("sakjung", Outcome.DRAW);
		playersOutcome.put("mediumBear", Outcome.LOSE);

		dealerOutcome.put(Outcome.WIN, 2);
		dealerOutcome.put(Outcome.DRAW, 1);
		dealerOutcome.put(Outcome.LOSE, 0);
	}

	@DisplayName("객체 생성 제대로 하는지")
	@Test
	void create() {
		OutcomeStatistics outcomeStatistics = new OutcomeStatistics(playersOutcome);
		assertThat(outcomeStatistics.getDealerOutcome()).isEqualTo(dealerOutcome);
		assertThat(outcomeStatistics.getPlayersOutcome()).isEqualTo(playersOutcome);
	}
}