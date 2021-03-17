package blackjack.controller.dto;

import java.util.List;

public class RoundStatusDto {
    private final List<String> dealerCardStatus;
    private final List<PlayerStatusDto> playerStatusDto;
    private final String dealerName;
    private final int dealerScore;

    public RoundStatusDto(String dealerName, List<String> dealerCardStatus, List<PlayerStatusDto> playerStatusDto, int dealerScore) {
        this.dealerName = dealerName;
        this.dealerCardStatus = dealerCardStatus;
        this.playerStatusDto = playerStatusDto;
        this.dealerScore = dealerScore;
    }

    public int getDealerScore() {
        return dealerScore;
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
