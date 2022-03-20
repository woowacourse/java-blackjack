package blackjack.dto;

import blackjack.domain.role.Role;

public class PlayerStatusDto {

	private final boolean finished;
	private final TableDto tableDto;

	private PlayerStatusDto(boolean finished, TableDto tableDto) {
		this.finished = finished;
		this.tableDto = tableDto;
	}

	public static PlayerStatusDto from(Role player) {
		return new PlayerStatusDto(player.isFinished(), TableDto.from(player));
	}

	public boolean isFinished() {
		return finished;
	}

	public TableDto getTableStatusDto() {
		return tableDto;
	}
}
