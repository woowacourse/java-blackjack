package blackjack.dto;

import blackjack.domain.Compete;
import java.util.List;
import java.util.stream.Collectors;

import blackjack.domain.Role;

public class FinalResultDto {

	private final DealerResultDto dealerResult;
	private final List<PlayerResultDto> playerResults;

	private FinalResultDto(final Role dealer, final List<Role> players, final Compete compete) {
		this.dealerResult = DealerResultDto.from(dealer, compete.getDealerCompeteResults());
		this.playerResults = players.stream()
				.map(player -> PlayerResultDto.from(player, compete.getPlayerCompeteResults(player)))
				.collect(Collectors.toList());
	}

	public static FinalResultDto from(final Role dealer, final List<Role> players,
									  final Compete compete) {
		return new FinalResultDto(dealer, players, compete);
	}

	public DealerResultDto getDealerResult() {
		return dealerResult;
	}

	public List<PlayerResultDto> getPlayerResults() {
		return playerResults;
	}
}
