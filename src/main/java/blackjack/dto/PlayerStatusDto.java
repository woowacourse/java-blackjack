package blackjack.dto;

import blackjack.domain.Role;

public class PlayerStatusDto {

	private final boolean draw;
	private final boolean hasNextPlayer;
	private final PlayerTableDto tableStatusDto;

	private PlayerStatusDto(boolean draw, boolean hasNextPlayer, PlayerTableDto tableStatusDto) {
		this.draw = draw;
		this.hasNextPlayer = hasNextPlayer;
		this.tableStatusDto = tableStatusDto;
	}

	public static PlayerStatusDto from(boolean draw, boolean hasNextPlayer, Role player) {
		return new PlayerStatusDto(draw, hasNextPlayer, PlayerTableDto.from(player));
	}

	public boolean isDraw() {
		return draw;
	}

	public boolean isHasNextPlayer() {
		return hasNextPlayer;
	}

	public PlayerTableDto getTableStatusDto() {
		return tableStatusDto;
	}
}
