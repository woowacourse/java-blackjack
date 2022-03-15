package blackjack.dto;

import blackjack.domain.role.PlayerTurns;
import blackjack.domain.role.Role;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerTurnsDto {

	private final List<String> names;

	private PlayerTurnsDto(final PlayerTurns playerTurns) {
		this.names = playerTurns.getPlayers().stream()
				.map(Role::getName)
				.collect(Collectors.toList());
	}

	public static PlayerTurnsDto from(final PlayerTurns playerTurns) {
		return new PlayerTurnsDto(playerTurns);
	}

	public List<String> getNames() {
		return names;
	}
}
