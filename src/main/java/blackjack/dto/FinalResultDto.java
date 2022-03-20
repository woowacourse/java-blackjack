package blackjack.dto;

import blackjack.domain.game.Revenue;
import blackjack.domain.role.Role;
import blackjack.domain.role.Roles;
import java.util.List;
import java.util.stream.Collectors;

public class FinalResultDto {

	private final ResultDto dealerResult;
	private final List<ResultDto> playerResults;

	private FinalResultDto(final Role dealer, final List<Role> players, final Revenue revenue) {
		this.dealerResult = ResultDto.from(dealer, revenue.getDealerRevenueResult());
		this.playerResults = players.stream()
				.map(player -> ResultDto.from(player, revenue.getPlayerRevenueResult(player)))
				.collect(Collectors.toList());
	}

	public static FinalResultDto from(final Roles roles, Revenue revenue) {
		return new FinalResultDto(roles.getDealer(), roles.getPlayers(), revenue);
	}

	public ResultDto getDealerResult() {
		return dealerResult;
	}

	public List<ResultDto> getPlayerResults() {
		return playerResults;
	}
}
