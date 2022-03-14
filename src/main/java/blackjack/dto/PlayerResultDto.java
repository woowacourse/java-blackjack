package blackjack.dto;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.result.GameResult;

public class PlayerResultDto {

    private final String name;
    private final String gameResult;

    private PlayerResultDto(String name, String gameResult) {
        this.name = name;
        this.gameResult = gameResult;
    }

    public static PlayerResultDto of(Player player, Dealer dealer) {
        GameResult gameResult = player.decideResult(dealer);
        return new PlayerResultDto(player.getName(), gameResult.getValue());
    }

    public String getName() {
        return name;
    }

    public String getGameResult() {
        return gameResult;
    }
}
