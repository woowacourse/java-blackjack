package blackjack.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.dto.DealerResultDto;
import blackjack.domain.dto.ResultDto;
import blackjack.domain.strategy.NumberGenerator;

public class ScoreBoardTest {

	@DisplayName("승패 결과 테스트")
	@Test
	void calculateResult() {
		NumberGenerator numberGenerator = new IntendedNumberGenerator(List.of(1, 2, 3, 4, 5, 6));
		Blackjack blackjack = new Blackjack(List.of("pobi", "jason"));
		blackjack.distributeInitialCards(numberGenerator);

		ResultDto dto = ScoreBoard.of(blackjack.getDealer(), blackjack.getPlayers());
		DealerResultDto dealerResultDto = dto.getDealerResultDto();
		List<String> dealerOutcome = dealerResultDto.getOutcome();

		assertThat(String.join(" ", dealerOutcome)).isEqualTo("2패");
	}
}
