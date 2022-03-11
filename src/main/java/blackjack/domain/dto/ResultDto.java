package blackjack.domain.dto;

public class ResultDto {
	private final DealerResultDto dealerResultDto;
	private final PlayerResultDto playerResultDto;

	public ResultDto(DealerResultDto dealerResultDto, PlayerResultDto playerResultDto) {
		this.dealerResultDto = dealerResultDto;
		this.playerResultDto = playerResultDto;
	}

	public DealerResultDto getDealerResultDto() {
		return dealerResultDto;
	}

	public PlayerResultDto getPlayerResultDto() {
		return playerResultDto;
	}
}
