package blackjack.dto;

import java.util.List;
import java.util.stream.Collectors;

import blackjack.domain.role.Role;

public class FinalResultDto {

	private final DealerResultDto dealerResults;
	private final List<PlayerResultDto> playerResults;

	private FinalResultDto(final Role dealer, final List<Role> players) {
		this.dealerResults = DealerResultDto.from(dealer);
		this.playerResults = players.stream()
			.map(PlayerResultDto::from)
			.collect(Collectors.toList());
	}

	public static FinalResultDto from(final Role dealer, final List<Role> players) {
		return new FinalResultDto(dealer, players);
	}

	public DealerResultDto getDealerResults() {
		return dealerResults;
	}

	public List<PlayerResultDto> getPlayerResults() {
		return playerResults;
	}
}
