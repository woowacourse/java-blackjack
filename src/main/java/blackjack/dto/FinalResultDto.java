package blackjack.dto;

import blackjack.domain.game.Revenue;
import blackjack.domain.role.Role;
import java.util.List;
import java.util.stream.Collectors;

public class FinalResultDto {

	private final DealerResultDto dealerResult;
	private final List<PlayerResultDto> playerResults;

	private FinalResultDto(final Role dealer, final List<Role> players, final Revenue revenue) {
		this.dealerResult = DealerResultDto.from(dealer, revenue.getDealerRevenueResult());
		this.playerResults = players.stream()
				.map(player -> PlayerResultDto.from(player, revenue.getPlayerRevenueResult(player)))
				.collect(Collectors.toList());
	}

	public static FinalResultDto from(final Role dealer, final List<Role> players, Revenue revenue) {
		return new FinalResultDto(dealer, players, revenue);
	}

	public DealerResultDto getDealerResult() {
		return dealerResult;
	}

	public List<PlayerResultDto> getPlayerResults() {
		return playerResults;
	}
}
