package view.dto;

public class GameResultDto {

    private final DealerResultDto dealerResultDto;
    private final PlayerResultsDto playerResultsDto;

    public GameResultDto(final DealerResultDto dealerResultDto, final PlayerResultsDto playerResultsDto) {
        this.dealerResultDto = dealerResultDto;
        this.playerResultsDto = playerResultsDto;
    }

    public DealerResultDto getDealerResult() {
        return dealerResultDto;
    }

    public PlayerResultsDto getPlayersResult() {
        return playerResultsDto;
    }
}
