package blackjack.dto;

import blackjack.domain.role.Role;

public class PlayerTurnDto {

	private final String name;

	private PlayerTurnDto(final String name) {
		this.name = name;
	}

	public static PlayerTurnDto from(final Role player) {
		return new PlayerTurnDto(player.getName());
	}

	public String getName() {
		return name;
	}
}
