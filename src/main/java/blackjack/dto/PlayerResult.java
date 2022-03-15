package blackjack.dto;

import blackjack.domain.GameResult;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public class PlayerResult {

    private final String name;
    private final String gameResult;

    public PlayerResult(Player player, Dealer dealer) {
        GameResult gameResult = player.decideResult(dealer);

        this.name = player.getName();
        this.gameResult = gameResult.getValue();
    }

    public String getName() {
        return name;
    }

    public String getGameResult() {
        return gameResult;
    }
}
