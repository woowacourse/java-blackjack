package blackjack.domain.participant;

import blackjack.domain.compete.CompeteResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Profit {
    
    private static final double BLACKJACK_RATIO = 1.5;
    private static final double NORMAL_RATIO = 1.0;
    private static final double DEFEAT_RATIO = -1.0;
    
    private static final int MINUS = -1;
    
    private final Map<Player, Double> profitOfPlayers;
    
    private Profit(Map<Player, Double> profitOfPlayers) {
        this.profitOfPlayers = profitOfPlayers;
    }
    
    public static Profit of(final Dealer dealer, final List<Player> players) {
        final Map<Player, Double> profitOfPlayers = new HashMap<>();
        for (Player player : players) {
            final double profit = calculateProfitOfPlayer(dealer, player);
            profitOfPlayers.put(player, profit);
        }
        return new Profit(profitOfPlayers);
    }
    
    private static double calculateProfitOfPlayer(Dealer dealer, Player player) {
        final double bettingMoney = player.getBettingMoney();
        
        if (CompeteResult.isPlayerWinAsBlackjack(dealer, player)) {
            return bettingMoney * BLACKJACK_RATIO;
        }
        
        if (CompeteResult.isPlayerDefeat(dealer, player)) {
            return bettingMoney * DEFEAT_RATIO;
        }
        
        return bettingMoney * NORMAL_RATIO;
    }
    
    public double getProfitOfPlayer(Player player) {
        return profitOfPlayers.get(player);
    }
    
    public double calculateProfitOfDealer() {
        return profitOfPlayers.values()
                              .stream()
                              .mapToDouble(profit -> profit * MINUS)
                              .sum();
    }
}
