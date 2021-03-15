package blackjack.domain.participant;

import blackjack.domain.compete.CompeteResult;

public class BettingMoney {
    
    private static final String ERROR_IS_NOT_DIGIT = "숫자만 입력해주세요";
    
    private static final double BLACKJACK_RATIO = 1.5;
    private static final double NON_BUST_RATIO = 1.0;
    private static final double BUST_RATIO = -1.0;
    
    private final double money;
    
    private BettingMoney(double money) {
        this.money = money;
    }
    
    public static BettingMoney from(String input) {
        if (!isDigit(input)) {
            throw new IllegalArgumentException(ERROR_IS_NOT_DIGIT);
        }
        
        return new BettingMoney(Double.parseDouble(input));
    }
    
    private static boolean isDigit(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
    
    public double getMoney() {
        return money;
    }
    
    public double calculateProfitOfPlayer(Dealer dealer, Player player) {
        if (isPlayerWinAsBlackjack(dealer, player)) {
            return money * BLACKJACK_RATIO;
        }
    
        if (CompeteResult.isPlayerDefeat(dealer, player)) {
            return money * BUST_RATIO;
        }
    
        return money * NON_BUST_RATIO;
    }
    
    private boolean isPlayerWinAsBlackjack(Dealer dealer, Player player) {
        final boolean isDealerBlackjack = dealer.cardHand.isBlackjack();
        final boolean isPlayerBlackjack = player.cardHand.isBlackjack();
        
        return !isDealerBlackjack && isPlayerBlackjack;
    }
}
