package domain.dto;

import java.util.List;

public class GameInitialInfoDto {

    private String dealerName;
    private String dealerOpenCard;
    private List<GameScoreResultDto> playerResults;

    public GameInitialInfoDto(String dealerName, String dealerOpenCard, List<GameScoreResultDto> playerResults) {
        this.dealerName = dealerName;
        this.dealerOpenCard = dealerOpenCard;
        this.playerResults = playerResults;
    }

    public String getDealerName() {
        return dealerName;
    }

    public String getDealerOpenCard() {
        return dealerOpenCard;
    }

    public List<GameScoreResultDto> getPlayerResults() {
        return playerResults;
    }
}
