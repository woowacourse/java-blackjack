package blackjack.view.dto;

import java.util.List;

public class RoundStatusDto {
    private final String dealerName;
    private final List<String> dealerCardStatus;
    private final List<PlayerStatusDto> playerStatusDto;

    public RoundStatusDto(String dealerName, List<String> dealerCardStatus, List<PlayerStatusDto> playerStatusDto) {
        this.dealerName = dealerName;
        this.dealerCardStatus = dealerCardStatus;
        this.playerStatusDto = playerStatusDto;
    }

    public String getDealerName() {
        return dealerName;
    }

    public List<String> getDealerCardStatus() {
        return dealerCardStatus;
    }

    public List<PlayerStatusDto> getPlayerStatusDto() {
        return playerStatusDto;
    }
}
