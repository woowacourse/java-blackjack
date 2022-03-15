package blackjack.dto;

import blackjack.domain.role.Role;

public class PlayerStatusDto {

	private final boolean draw;
	private final PlayerTableDto tableStatusDto;

	private PlayerStatusDto(boolean draw, PlayerTableDto tableStatusDto) {
		this.draw = draw;
		this.tableStatusDto = tableStatusDto;
	}

	public static PlayerStatusDto from(boolean draw, Role player) {
		return new PlayerStatusDto(draw, PlayerTableDto.from(player));
	}

	public boolean isDraw() {
		return draw;
	}

	public PlayerTableDto getTableStatusDto() {
		return tableStatusDto;
	}
}
