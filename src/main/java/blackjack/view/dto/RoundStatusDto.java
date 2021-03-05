package blackjack.view.dto;

import java.util.List;

public class RoundStatusDto {
    private final String dealerName;
    private final List<String> dealerCardStatus;
    private final List<PlayerStatusDto> playerStatusDto;
    private final int dealerScore;

    public RoundStatusDto(final String dealerName, final List<String> dealerCardStatus, final List<PlayerStatusDto> playerStatusDto, final int dealerScore) {
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
