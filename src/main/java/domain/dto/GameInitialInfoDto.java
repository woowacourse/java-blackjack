package domain.dto;

import domain.participant.Dealer;
import domain.participant.Players;

import java.util.List;

public class GameInitialInfoDto {

    private String dealerName;
    private String dealerOpenCard;
    private List<GameScoreResultDto> playerResults;

    private GameInitialInfoDto(String dealerName, String dealerOpenCard, List<GameScoreResultDto> playerResults) {
        this.dealerName = dealerName;
        this.dealerOpenCard = dealerOpenCard;
        this.playerResults = playerResults;
    }

    public static GameInitialInfoDto of(Dealer dealer, Players players) {
        return new GameInitialInfoDto(
                dealer.getName(),
                dealer.getOpenCard(),
                GameScoreResultDto.from(players)
        );
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
