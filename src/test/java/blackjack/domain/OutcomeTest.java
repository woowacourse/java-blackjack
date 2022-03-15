package blackjack.domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Outcome 테스트")
class OutcomeTest {

	@DisplayName("플레이어와 딜러의 스코어 차이에 따라 결과아 제대로 나오는지 확인")
	@ParameterizedTest(name = "{index} {displayName} playerScore={0} dealerScore={0} expectedOutcome={3}")
	@CsvSource(value = {"1, 2, DEFEAT", "2, 1, VICTORY", "2, 2, TIE"})
	void checkProperOutCome(final int playerScore, final int dealerScore, final Outcome expectedOutcome) {
		assertThat(Outcome.of(playerScore, dealerScore)).isEqualTo(expectedOutcome);
	}

}