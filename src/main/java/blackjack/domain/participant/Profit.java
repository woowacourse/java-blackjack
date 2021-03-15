package blackjack.domain.participant;

import blackjack.domain.compete.CompeteResult;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.*;

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
        final Map<Player, Double> profitOfPlayers = players.stream()
                                                           .collect(groupingBy(Function.identity(),
                                                                   mapping(player -> calculateProfitOfPlayer(dealer, player),
                                                                           reducing(null, (preProfit, postProfit) -> postProfit))));
        
        return new Profit(profitOfPlayers);
    }
    
    private static double calculateProfitOfPlayer(Dealer dealer, Player player) {
        final double bettingMoney = player.getBettingMoney();
        
        if (isPlayerWinAsBlackjack(dealer, player)) {
            return bettingMoney * BLACKJACK_RATIO;
        }
        
        if (CompeteResult.isPlayerDefeat(dealer, player)) {
            return bettingMoney * DEFEAT_RATIO;
        }
        
        return bettingMoney * NORMAL_RATIO;
    }
    
    private static boolean isPlayerWinAsBlackjack(Dealer dealer, Player player) {
        final boolean isDealerBlackjack = dealer.cardHand.isBlackjack();
        final boolean isPlayerBlackjack = player.cardHand.isBlackjack();
        
        return !isDealerBlackjack && isPlayerBlackjack;
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
