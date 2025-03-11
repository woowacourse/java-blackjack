package blackjack.domain.card_hand;

public interface BlackjackWinDeterminable {
    
    int getBlackjackSum();
    boolean isBust();
    boolean isBlackjack();
}
