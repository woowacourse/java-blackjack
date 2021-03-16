package blackjack.domain.compete;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public enum CompeteResult {
    WIN("승"), DRAW("무"), DEFEAT("패");
    
    private final String competeResult;
    
    CompeteResult(String competeResult) {
        this.competeResult = competeResult;
    }
    
    public static CompeteResult getCompeteResultOfPlayer(Dealer dealer, Player player) {
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
    
    public static boolean isPlayerDefeat(Dealer dealer, Player player) {
        return (dealer.sumCardHand() > player.sumCardHand()) || player.isBust();
    }
    
    public static boolean isPlayerWinAsBlackjack(Dealer dealer, Player player) {
        return !dealer.isBlackjack() && player.isBlackjack();
    }
    
    public String getCompeteResult() {
        return competeResult;
    }
}
