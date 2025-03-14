package blackjack.domain.game;

import java.util.Collections;
import java.util.List;

public class PlayerProfits {

    private final List<PlayerProfit> playerProfits;

    public PlayerProfits(List<PlayerProfit> playerProfits) {
        this.playerProfits = playerProfits;
    }

    public int calculateDealerProfit() {
        int playerProfitSum = playerProfits.stream()
                .mapToInt(PlayerProfit::getProfit)
                .sum();
        return playerProfitSum * -1;
    }

    public List<PlayerProfit> getPlayerProfits() {
        return Collections.unmodifiableList(playerProfits);
    }
}
