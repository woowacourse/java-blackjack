package blackjack.domain.dto;

public class RecordsDto {
    private final DealerRecordDto dealerRecordDto;
    private final PlayerRecordDto playerRecordDto;

    public RecordsDto(DealerRecordDto dealerRecordDto, PlayerRecordDto playerRecordDto) {
        this.dealerRecordDto = dealerRecordDto;
        this.playerRecordDto = playerRecordDto;
    }

    public DealerRecordDto getDealerResultDto() {
        return dealerRecordDto;
    }

    public PlayerRecordDto getPlayerResultDto() {
        return playerRecordDto;
    }
}
