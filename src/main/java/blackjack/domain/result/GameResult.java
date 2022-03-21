package blackjack.domain.result;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.PlayerGroup;
import java.util.LinkedHashMap;
import java.util.Map;

public class GameResult {
    private static final int NO_PROFIT = 0;
    private static final double BLACKJACK_PROFIT = 1.5;

    private final Map<String, Integer> profitResults;

    private GameResult(Map<String, Integer> profitResults) {
        this.profitResults = profitResults;
    }

    public static GameResult of(Dealer dealer, PlayerGroup playerGroup) {
        Map<String, Integer> profitResults = new LinkedHashMap<>();
        Map<String, Integer> playerProfits = getPlayerProfits(dealer, playerGroup);
        profitResults.put(dealer.getName(), calculateDealerProfit(playerProfits));
        profitResults.putAll(playerProfits);
        return new GameResult(profitResults);
    }

    private static Map<String, Integer> getPlayerProfits(Dealer dealer, PlayerGroup playerGroup) {
        Map<String, Integer> playerProfits = new LinkedHashMap<>();
        for (Player player : playerGroup.getPlayers()) {
            playerProfits.put(player.getName(), calculateProfit(player, dealer));
        }
        return playerProfits;
    }

    private static int calculateProfit(Player player, Dealer dealer) {
        if (player.isBlackJack()) {
            return calculateBlackJackProfit(player, dealer);
        }
        return calculateGeneralProfit(player, dealer);
    }

    private static int calculateBlackJackProfit(Player player, Dealer dealer) {
        if (dealer.isBlackJack()) {
            return NO_PROFIT;
        }
        return (int) (player.getBettingMoney().getAmount() * BLACKJACK_PROFIT);
    }

    private static int calculateGeneralProfit(Player player, Dealer dealer) {
        Match matchResult = player.compareCardsSumTo(dealer.getScore());
        if (matchResult == Match.WIN) {
            return player.getBettingMoney().getAmount();
        }
        if (matchResult == Match.LOSE) {
            return -player.getBettingMoney().getAmount();
        }
        return NO_PROFIT;
    }

    private static Integer calculateDealerProfit(Map<String, Integer> playerProfits) {
        return -playerProfits.values()
                .stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    public Map<String, Integer> getProfitResults() {
        return new LinkedHashMap<>(profitResults);
    }
}
