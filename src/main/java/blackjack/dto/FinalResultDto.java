package blackjack.dto;

import java.util.List;
import java.util.Map;

import blackjack.domain.Outcome;
import blackjack.domain.role.Role;

public class FinalResultDto {

	private final DealerResultDto dealerResults;
	private final List<PlayerResultDto> playerResults;

	private FinalResultDto(final Role dealer, final Map<Outcome, Integer> dealerResult,
		final List<PlayerResultDto> playerResult) {
		this.dealerResults = DealerResultDto.from(dealer, dealerResult);
		this.playerResults = playerResult;
	}

	public static FinalResultDto from(final Role dealer, final Map<Outcome, Integer> dealerResult,
		final List<PlayerResultDto> playersResult) {
		return new FinalResultDto(dealer, dealerResult, playersResult);
	}

	public DealerResultDto getDealerResults() {
		return dealerResults;
	}

	public List<PlayerResultDto> getPlayerResults() {
		return playerResults;
	}
}
