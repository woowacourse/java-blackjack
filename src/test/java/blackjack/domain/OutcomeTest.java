package blackjack.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Outcome 테스트")
class OutcomeTest {

	@DisplayName("승패를 결정해주는 메서드가 올바르게 리턴하는지 확인")
	@ParameterizedTest(name = "{index} {displayName} player: {0} dealer:{1} expectedOutcome: {2}")
	@CsvSource(value = {"21, 20, VICTORY", "18, 20, DEFEAT", "19 ,19, TIE"})
	void check_Outcome_Of(int playerScore, int dealerScore, Outcome expectedOutcome) {
		assertThat(Outcome.of(playerScore, dealerScore)).isEqualTo(expectedOutcome);
	}

	@DisplayName("승패를 결정해주는 메서드가 올바르게 리턴하는지 확인")
	@ParameterizedTest(name = "{index} {displayName} player: {0} dealer:{1} expectedOutcome: {2}")
	@CsvSource(value = {"true, false, VICTORY", "false, true, DEFEAT", "true, true, TIE", "false, false, TIE"})
	void check_Outcome_Of_BlackJack(boolean playerBlackJack, boolean dealerBlackJack, Outcome expectedOutcome) {
		assertThat(Outcome.ofBlackJack(playerBlackJack, dealerBlackJack)).isEqualTo(expectedOutcome);
	}
}
