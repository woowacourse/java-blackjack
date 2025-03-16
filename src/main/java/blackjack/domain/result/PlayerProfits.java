package blackjack.domain.result;

import blackjack.domain.game.Player;
import java.util.ArrayList;
import java.util.List;

public class PlayerProfits {
    private final List<PlayerProfit> playerProfits;

    public PlayerProfits() {
        this.playerProfits = new ArrayList<>();
    }

    public void add(PlayerProfit playerProfit) {
        this.playerProfits.add(playerProfit);
    }

    public PlayerProfit findPlayerProfitByPlayer(Player player) {
        return playerProfits.stream()
                .filter(profit -> profit.isResultOf(player))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("플레이어에 해당하는 결과가 존재하지 않습니다."));
    }
}
