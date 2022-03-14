package blackjack.domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.dto.DealerResultDto;
import blackjack.domain.dto.PlayerResultDto;
import blackjack.domain.dto.ResultDto;
import blackjack.domain.machine.Blackjack;
import blackjack.domain.machine.Records;

public class RecordsTest {
	private ResultDto dto;

	@BeforeEach
	void setUp() {
		List<String> playerNames = List.of("범블비", "잉");
		Blackjack blackjack = new Blackjack(playerNames);
		IntendedNumberGenerator intendedNumberGenerator = new IntendedNumberGenerator(List.of(1, 2, 3, 11, 15, 9));
		blackjack.dealInitialCards(intendedNumberGenerator);

		//dealer: 12점, player: 7점(범블비), 14점(잉)
		dto = Records.of(blackjack.getDealer(), blackjack.getPlayers());
	}

	@DisplayName("딜러 전적 테스트")
	@Test
	void dealerRecords() {
		DealerResultDto dealerResultDto = dto.getDealerResultDto();

		Map<String, Integer> actual = dealerResultDto.getOutcome();

		Map<String, Integer> expected = new LinkedHashMap<>();
		expected.put("승", 1);
		expected.put("무", 0);
		expected.put("패", 1);

		assertThat(actual).isEqualTo(expected);
	}

	@DisplayName("플레이 전적 테스트")
	@Test
	void playersRecords() {
		PlayerResultDto playerResultDto = dto.getPlayerResultDto();

		Map<String, String> actual = playerResultDto.getOutcome();

		Map<String, String> expected = new LinkedHashMap<>();
		expected.put("범블비", "패");
		expected.put("잉", "승");

		assertThat(actual).isEqualTo(expected);
	}
}
