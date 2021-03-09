package blackjack.domain.compete;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.*;

public enum CompeteResult {
    WIN("승"), DRAW("무"), DEFEAT("패");
    
    private final String competeResult;
    
    CompeteResult(String competeResult) {
        this.competeResult = competeResult;
    }
    
    public static CompeteResultGroup compete(final Dealer dealer, final List<Player> players) {
        final Map<Player, CompeteResult> collect = players.stream()
                                                          .collect(groupingBy(Function.identity(),
                                                                  mapping(player -> CompeteResult.getCompeteResultOfPlayer(dealer, player), reducing(null, (competeResult1, competeResult2) -> competeResult2))));
        
        return new CompeteResultGroup(collect);
    }
    
    private static CompeteResult getCompeteResultOfPlayer(Dealer dealer, Player player) {
        if (isPlayerWin(dealer, player)) {
            return CompeteResult.WIN;
        }
        
        if (isPlayerDefeat(dealer, player)) {
            return CompeteResult.DEFEAT;
        }
        
        return CompeteResult.DRAW;
    }
    
    private static boolean isPlayerWin(Dealer dealer, Player player) {
        return ((dealer.sumCardHand() < player.sumCardHand()) && !player.isBust());
    }
    
    private static boolean isPlayerDefeat(Dealer dealer, Player player) {
        return (dealer.sumCardHand() > player.sumCardHand()) || player.isBust();
    }
    
    public String getCompeteResult() {
        return competeResult;
    }
}
