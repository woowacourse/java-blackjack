package blackjack.domain.result;

import blackjack.domain.playing.user.Player;

import java.util.LinkedHashMap;
import java.util.Map;

public class BettingMoneyRepository {
    private static Map<Player, BettingMoney> bettingRepository = new LinkedHashMap<>();

    public static void save(Player player, BettingMoney bettingMoney) {
        bettingRepository.put(player, bettingMoney);
    }

    public static BettingMoney findBy(Player player) {
        return bettingRepository.get(player);
    }
}
