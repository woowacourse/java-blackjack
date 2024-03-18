package blackjack.dto;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Money;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.PlayerBetAmounts;
import java.util.LinkedHashMap;
import java.util.Map;

public class GamerEarningsDto {

    private final Map<String, Long> nameEarningsMap;

    private GamerEarningsDto(Map<String, Long> nameEarningsMap) {
        this.nameEarningsMap = nameEarningsMap;
    }

    public static GamerEarningsDto fromDealerAndPlayers(Dealer dealer, PlayerBetAmounts playerBetAmounts) {
        Map<String, Long> nameEarningsMap = new LinkedHashMap<>();
        nameEarningsMap.put(dealer.getName(), dealer.calculateOwnEarning(playerBetAmounts).amount());

        for (Player player : playerBetAmounts.getPlayers()) {
            String playerName = player.getName();
            Money playerEarning = dealer.informPlayerEarning(player, playerBetAmounts.getBetAmount(player));
            nameEarningsMap.put(playerName, playerEarning.amount());
        }

        return new GamerEarningsDto(nameEarningsMap);
    }

    public Map<String, Long> nameEarningsMap() {
        return nameEarningsMap;
    }
}
