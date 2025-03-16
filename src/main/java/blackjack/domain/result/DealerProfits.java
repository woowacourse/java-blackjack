package blackjack.domain.result;

import blackjack.domain.game.Player;
import java.util.HashMap;
import java.util.Map;

public class DealerProfits {
    private final Map<Player, DealerProfit> playerProfits;

    public DealerProfits() {
        this.playerProfits = new HashMap<>();
    }

    public void add(Player player, DealerProfit dealerProfit) {
        if (playerProfits.containsKey(player)) {
            throw new IllegalArgumentException("이미 저장된 플레이어에 대한 딜러의 수익입니다.");
        }
        this.playerProfits.put(player, dealerProfit);
    }

    public DealerProfit findByPlayer(Player player) {
        return playerProfits.entrySet().stream()
                .filter(entry -> entry.getKey().equals(player))
                .findAny()
                .map(Map.Entry::getValue)
                .orElseThrow(() -> new IllegalArgumentException("플레이어에 해당하는 결과가 존재하지 않습니다."));
    }
}
